package com.sunrise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: sunrise
 * @description: 验证模块启动入口
 * @author: T.LM
 * @date: 2023-03-01 21:50
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class VerifyApplication {
    public static void main(String[] args) {
        SpringApplication.run(VerifyApplication.class, args);
    }
}
