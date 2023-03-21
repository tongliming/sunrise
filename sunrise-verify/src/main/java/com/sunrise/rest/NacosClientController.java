package com.sunrise.rest;

import com.sunrise.nacos.NacosClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: sunrise
 * @description: Nacos服务发现
 * @author: T.LM
 * @date: 2023-03-01 22:23
 **/
@RestController
@RequestMapping
@RequiredArgsConstructor
public class NacosClientController {

    private final NacosClientService nacosClientService;

    @GetMapping("instances")
    public List<ServiceInstance> getService(@RequestParam(defaultValue = "sunrise-verify") String serviceId) {
        return nacosClientService.getNacosClientInstance(serviceId);
    }

}
