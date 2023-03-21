package com.sunrise.service;

import com.sunrise.vo.UsernameAndPassword;

/**
 * @program: sunrise
 * @description: JWT相关服务接口
 * @author: Mr.Wang
 * @data: 2023-03-02 20:13
 **/
public interface IJwtService {

    /************************************************
    * @Description: 生成JWT Token
    * @Param: [username, password]
    * @return: java.lang.String
    * @Author: T.LM
    * @Date: 2023/3/2 20:15
    * ***********************************************/
    String generateToken(String username, String password) throws Exception;

    /************************************************
    * @Description: 生成JWT Token，并指定过期时间（单位：天）
    * @Param: [username, password, expired]
    * @return: java.lang.String
    * @Author: T.LM
    * @Date: 2023/3/2 20:16
    * ***********************************************/
    String generateToken(String username, String password, int expired) throws Exception;

    /************************************************
    * @Description: 用户注册并生成JWT Token
    * @Param: [usernameAndPassword]
    * @return: java.lang.String
    * @Author: T.LM
    * @Date: 2023/3/2 20:17
    * ***********************************************/
    String registerAndGenerateToken(UsernameAndPassword usernameAndPassword) throws Exception;
}
