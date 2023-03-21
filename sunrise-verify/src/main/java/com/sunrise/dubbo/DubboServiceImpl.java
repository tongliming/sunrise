package com.sunrise.dubbo;

import org.springframework.stereotype.Service;

/**
 * @program: sunrise
 * @description: Dubbo测试
 * @author: T.LM
 * @date: 2023-03-05 00:52
 **/
@Service
public class DubboServiceImpl implements DubboService {
    @Override
    public String dubbo() {
        return "Hello World";
    }
}
