package com.sunrise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @program: sunrise
 * @description: 授权鉴权中心启动
 * @author: T.LM
 * @date: 2023-03-01 22:39
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaAuditing  //允许jpa自动审计
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
