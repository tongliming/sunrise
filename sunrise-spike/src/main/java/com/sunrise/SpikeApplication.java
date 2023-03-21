package com.sunrise;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: sunrise
 * @description: 授权鉴权中心启动
 * @author: T.LM
 * @date: 2023-03-01 22:39
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.sunrise.mapper")
public class SpikeApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpikeApplication.class, args);
    }
}
