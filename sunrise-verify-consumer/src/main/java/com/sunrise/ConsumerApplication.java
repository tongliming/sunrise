package com.sunrise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: sunrise
 * @description:
 * @author: T.LM
 * @date: 2023-03-05 13:23
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}
