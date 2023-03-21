package com.sunrise.controller;

import com.sunrise.dubbo.DubboService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: sunrise
 * @description:
 * @author: T.LM
 * @date: 2023-03-05 13:28
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping
public class ConsumerController {

    DubboService dubboService;

    @GetMapping("/dubbo")
    public String testDubbo() {
        return dubboService.dubbo();
    }
}
