package com.sunrise.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.sunrise.mapper.SeckillVouchersMapper;
import com.sunrise.mapper.VoucherOrdersMapper;
import com.sunrise.model.pojo.SeckillVouchers;
import com.sunrise.model.pojo.VoucherOrders;
import com.sunrise.model.vo.LoginUserInfo;
import com.sunrise.util.AssertUtil;
import com.sunrise.util.TokenParseUtil;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 秒杀业务逻辑层
 */
@Service
public class SeckillService {

    private static final String SVKEY = "seckill:voucher:";
    @Resource
    private SeckillVouchersMapper seckillVouchersMapper;
    @Resource
    private VoucherOrdersMapper voucherOrdersMapper;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private DefaultRedisScript defaultRedisScript;

    /**
     * 抢购代金券
     *
     * @param voucherId   代金券 ID
     * @param accessToken 登录token
     * @Para path 访问路径
     */
    @Transactional(rollbackFor = Exception.class)
    public int doSeckill(Integer voucherId, String accessToken, String path) throws Exception {
        // 基本参数校验
        AssertUtil.isTrue(voucherId == null || voucherId < 0, "请选择需要抢购的代金券");
        AssertUtil.isNotEmpty(accessToken, "请登录");

        // 注释原始的 关系型数据库 的流程
        // 判断此代金券是否加入抢购
//        SeckillVouchers seckillVouchers = seckillVouchersMapper.selectVoucher(voucherId);
        String key = SVKEY + voucherId;
        Map<String, Object> map = redisTemplate.opsForHash().entries(key);
        SeckillVouchers seckillVouchers = BeanUtil.mapToBean(map, SeckillVouchers.class, true, null);
        AssertUtil.isTrue(seckillVouchers == null, "该代金券并未有抢购活动");
        // 判断是否有效
        AssertUtil.isTrue(seckillVouchers.getIsValid() == 0, "该活动已结束");

        // 判断是否开始、结束
        Date now = new Date();
        AssertUtil.isTrue(now.before(seckillVouchers.getStartTime()), "该抢购还未开始");
        AssertUtil.isTrue(now.after(seckillVouchers.getEndTime()), "该抢购已结束");
        // 判断是否卖完
        AssertUtil.isTrue(seckillVouchers.getAmount() < 1, "该券已经卖完了");
        // 获取登录用户信息
        LoginUserInfo loginUserInfo = TokenParseUtil.parseFromToken(accessToken);

        // 判断登录用户是否已抢到(一个用户针对这次活动只能买一次)
        VoucherOrders order = voucherOrdersMapper.findDinerOrder(loginUserInfo.getId(),
                seckillVouchers.getFkVoucherId());
        AssertUtil.isTrue(order != null, "该用户已抢到该代金券，无需再抢");

        // 下单
        VoucherOrders voucherOrders = new VoucherOrders();
        voucherOrders.setFkDinerId(loginUserInfo.getId());
        // Redis 中不需要维护外键信息
        // voucherOrders.setFkSeckillId(seckillVouchers.getId());
        voucherOrders.setFkVoucherId(seckillVouchers.getFkVoucherId());
        String orderNo = IdUtil.getSnowflake(1, 1).nextIdStr();
        voucherOrders.setOrderNo(orderNo);
        voucherOrders.setOrderType(1);
        voucherOrders.setStatus(0);
        int countOrder = voucherOrdersMapper.save(voucherOrders);
        AssertUtil.isTrue(countOrder == 0, "用户抢购失败");

        // 扣库存
//        Long amount = redisTemplate.opsForHash().increment(key, "amount", -1);
//        int count = seckillVouchersMapper.stockDecrease(seckillVouchers.getId());
//        AssertUtil.isTrue(amount < 0, "该券已经卖完了");
        List<String> keys = new ArrayList<>();
        keys.add(key);
        keys.add("amount");
        Long amount = (Long) redisTemplate.execute(defaultRedisScript, keys);
        AssertUtil.isTrue(amount == null || amount < 1, "该券已经卖完了");
        return countOrder;
    }

    /**
     * 添加需要抢购的代金券
     *
     * @param seckillVouchers
     */
    @Transactional(rollbackFor = Exception.class)
    public void addSeckillVouchers(SeckillVouchers seckillVouchers) {
        // 非空校验
        AssertUtil.isTrue(seckillVouchers.getFkVoucherId() == null, "请选择需要抢购的代金券");
        AssertUtil.isTrue(seckillVouchers.getAmount() == 0, "请输入抢购总数量");
        Date now = new Date();
        AssertUtil.isNotNull(seckillVouchers.getStartTime(), "请输入开始时间");
        // 生产环境下面一行代码需放行，这里注释方便测试
        // AssertUtil.isTrue(now.after(seckillVouchers.getStartTime()), "开始时间不能早于当前时间");
        AssertUtil.isNotNull(seckillVouchers.getEndTime(), "请输入结束时间");
        AssertUtil.isTrue(now.after(seckillVouchers.getEndTime()), "结束时间不能早于当前时间");
        AssertUtil.isTrue(seckillVouchers.getStartTime().after(seckillVouchers.getEndTime()), "开始时间不能晚于结束时间");

        // 注释原始的走 关系型数据库 的流程
        // 验证数据库中是否已经存在该券的秒杀活动
//        SeckillVouchers seckillVouchersFromDb = seckillVouchersMapper.selectVoucher(seckillVouchers.getFkVoucherId());
//        AssertUtil.isTrue(seckillVouchersFromDb != null, "该券已经拥有了抢购活动");
        // 插入数据库
//        seckillVouchersMapper.save(seckillVouchers);
        String key = SVKEY + seckillVouchers.getFkVoucherId();
        Map<String, Object> map = redisTemplate.opsForHash().entries(key);
        AssertUtil.isTrue(!map.isEmpty() && (int) map.get("amount") > 0, "该券已拥有抢购活动");
        // 插入Redis
        seckillVouchers.setIsValid(1);
        seckillVouchers.setCreateDate(now);
        seckillVouchers.setUpdateDate(now);
        redisTemplate.opsForHash().putAll(key, BeanUtil.beanToMap(seckillVouchers));
    }

}