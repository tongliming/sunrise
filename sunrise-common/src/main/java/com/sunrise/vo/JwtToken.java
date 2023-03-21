package com.sunrise.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: sunrise
 * @description: 授权中心鉴权后提供给客户端的Token
 * @author: T.LM
 * @date: 2023-03-02 20:09
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtToken implements Serializable {
    private String token;
}
