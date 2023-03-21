package com.sunrise.constant;

/**
 * <h1>网关常量定义</h1>
 * */
public class GatewayConstant {

    /** 登录 uri */
    public static final String LOGIN_URI = "/login";

    /** 注册 uri */
    public static final String REGISTER_URI = "/register";
    /** 商家添加代金券 uri */
    public static final String SECKILL_ADD = "/spike/add";

    /** 去授权中心拿到登录 token 的 uri 格式化接口 */
    public static final String AUTHORITY_CENTER_TOKEN_URL_FORMAT =
            "http://%s:%s/auth/token";

    /** 去授权中心注册并拿到 token 的 uri 格式化接口 */
    public static final String AUTHORITY_CENTER_REGISTER_URL_FORMAT =
            "http://%s:%s/auth/register";
}
