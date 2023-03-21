package com.sunrise.service;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSON;
import com.sunrise.constant.AuthorityConstant;
import com.sunrise.constant.CommonConstant;
import com.sunrise.dao.SunriseUserDao;
import com.sunrise.entity.SunriseUser;
import com.sunrise.vo.LoginUserInfo;
import com.sunrise.vo.UsernameAndPassword;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @program: sunrise
 * @description: JWT相关服务接口实现
 * @author: T.LM
 * @date: 2023-03-02 20:14
 **/
@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class JwtServiceImpl implements IJwtService {

    private final SunriseUserDao sunriseUserDao;


    /************************************************
     * @Description: 生成JWT Token
     * @Param: [username, password]
     * @return: java.lang.String
     * @Author: T.LM
     * @Date: 2023/3/2 20:15
     * ***********************************************/
    @Override
    public String generateToken(String username, String password) throws Exception {
        return generateToken(username, password, 0);
    }

    /************************************************
     * @Description: 生成JWT Token，并指定过期时间（单位：天）
     * @Param: [username, password, expired]
     * @return: java.lang.String
     * @Author: T.LM
     * @Date: 2023/3/2 20:16
     * ***********************************************/
    @Override
    public String generateToken(String username, String password, int expired) throws Exception {
        SunriseUser sunriseUser = sunriseUserDao.findByUsernameAndPassword(username, password);
        if (sunriseUser == null) {
            log.error("can not find user, username [{}], password [{}]", username, password);
            return null;
        }
        if (expired <= 0) {
            expired = AuthorityConstant.TOKEN_EXPIRED_DAY;
        }

        LoginUserInfo loginUserInfo = new LoginUserInfo(sunriseUser.getId(), username);

        // 计算Token超时时间
        ZonedDateTime zdt = LocalDate.now().plus(expired, ChronoUnit.DAYS)
                .atStartOfDay(ZoneId.systemDefault());
        Date expireDate = Date.from(zdt.toInstant());

        return Jwts.builder()
                // jwt payload
                .claim(CommonConstant.JWT_USER_INFO_KEY, JSON.toJSONString(loginUserInfo))
                // jwt id
                .setId(UUID.randomUUID().toString())
                // jwt 过期时间
                .setExpiration(expireDate)
                // jwt 签名，加密
                .signWith(getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }

    /************************************************
     * @Description: 用户注册并生成JWT Token
     * @Param: [usernameAndPassword]
     * @return: java.lang.String
     * @Author: T.LM
     * @Date: 2023/3/2 20:17
     * ***********************************************/
    @Override
    public String registerAndGenerateToken(UsernameAndPassword usernameAndPassword) throws Exception {
        SunriseUser oldUser = sunriseUserDao.findByUsername(usernameAndPassword.getUsername());
        if (oldUser != null) {
            log.error("username has registered, [{}]", oldUser.getUsername());
            return null;
        }
        SunriseUser sunriseUser = new SunriseUser();
        sunriseUser.setUsername(usernameAndPassword.getUsername());
        sunriseUser.setPassword(usernameAndPassword.getPassword()); // MD5加密过
        sunriseUser.setExtraInfo("{}");

        sunriseUser = sunriseUserDao.save(sunriseUser);
        log.info("register user success, [{}]", JSON.toJSONString(sunriseUser));

        return generateToken(sunriseUser.getUsername(), sunriseUser.getPassword());
    }

    private PrivateKey getPrivateKey() throws Exception {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(
                Base64.decode(AuthorityConstant.AUTH_PRIVATE_KEY)
        );
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(spec);
    }
}
