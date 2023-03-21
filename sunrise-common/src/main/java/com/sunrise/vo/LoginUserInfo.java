package com.sunrise.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: sunrise
 * @description: 登录用户信息
 * @author: T.LM
 * @date: 2023-03-02 20:11
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserInfo {
    /** 用户 id */
    private Long id;
    /** 用户名 */
    private String username;
}
