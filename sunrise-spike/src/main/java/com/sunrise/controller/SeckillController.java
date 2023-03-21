package com.sunrise.controller;

import com.sunrise.model.pojo.SeckillVouchers;
import com.sunrise.service.SeckillService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 秒杀控制层
 */
@RestController
public class SeckillController {

    @Resource
    private SeckillService seckillService;
    @Resource
    private HttpServletRequest request;

    /**
     * 秒杀下单
     *
     * @param voucherId
     * @param access_token
     * @return
     */
    @PostMapping("{voucherId}")
    public int doSeckill(@PathVariable Integer voucherId, String access_token) throws Exception {
        return seckillService.doSeckill(voucherId, access_token, request.getServletPath());
    }

    /**
     * 新增秒杀活动
     *
     * @param seckillVouchers
     * @return
     */
    @PostMapping("add")
    public int addSeckillVouchers(@RequestBody SeckillVouchers seckillVouchers) {
        seckillService.addSeckillVouchers(seckillVouchers);
        return 1;
    }

}