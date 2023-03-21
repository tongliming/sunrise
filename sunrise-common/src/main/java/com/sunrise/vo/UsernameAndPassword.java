package com.sunrise.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: sunrise
 * @description: 登录对象
 * @author: T.LM
 * @date: 2023-03-02 20:09
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsernameAndPassword {
    private String username;
    private String password;
}
