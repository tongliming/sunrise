package com.sunrise.util;

import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson2.JSON;
import com.sunrise.constant.CommonConstant;
import com.sunrise.model.vo.LoginUserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;

/**
 * @program: sunrise
 * @description: JWT Token 解析工具类
 * @author: T.LM
 * @date: 2023-03-02 20:59
 **/
public class TokenParseUtil {

    /************************************************
    * @Description: 解析 token, 获取 LoginUserInfo
    * @Param: [token]
    * @return: com.sunrise.vo.LoginUserInfo
    * @Author: T.LM
    * @Date: 2023/3/2 21:25
    * ***********************************************/
    public static LoginUserInfo parseFromToken(String token) throws Exception {
        if (token == null) {
            return null;
        }
        Jws<Claims> claimsJws = parseToken(token, getPublicKey());
        Claims body = claimsJws.getBody();
        // 判断token是否过期
        if (body.getExpiration().before(Calendar.getInstance().getTime())) {
            return null;
        }
        return JSON.parseObject(body.get(CommonConstant.JWT_USER_INFO_KEY).toString(), LoginUserInfo.class);
    }

    private static Jws<Claims> parseToken(String token, PublicKey publicKey) throws Exception {
        return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token);
    }

    private static PublicKey getPublicKey() throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(
                Base64.decode(CommonConstant.AUTH_PUBLIC_KEY)
        );
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    public static void main(String[] args) throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJTdW5yaXNlLVVzZXIiOiJ7XCJpZFwiOjU5OTIsXCJ1c2VybmFtZVwiOlwidGVzdDE5OTVcIn0iLCJqdGkiOiI3ZjhlNzY5OC1iYWMwLTRmNTMtOGJjOC01ZjdmMjcwMTBjZWUiLCJleHAiOjE2ODAxOTIwMDB9.hHsgrjhpFdYNfMAtsN7wDXzcuN9v9bvbQ3e7oRtYnNpReLk5BO17NLWq6C-Qz-0m4hghKzQ_aYhrzA_6M8SeVbqLKkGllkEr-HrlRfUOKZpikEJ9uum-jfdHy8Yp-WDob5dDjwTdUFryySQRj_F02iihgNb1LY_O_UWGccPhX6_sbEWgahwPofw5iZQ13f1yAWhS09DSCF0yONAmcvki6YWJYwQbA75C9PiSNuQ0wf-CLHWW7NwvewrM81JQnRMvdNQSXlMwIeTcfIJO_aARPJvkYM5XrU1J4271NvA8p_Cm70TN1pxa-zyRdydVg7hQ57t89LYIRN2H3mVGxT_EfQ";
        LoginUserInfo loginUserInfo = parseFromToken(token);
    }

}
