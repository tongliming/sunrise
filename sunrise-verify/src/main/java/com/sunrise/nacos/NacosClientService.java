package com.sunrise.nacos;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: sunrise
 * @description: Nacos服务发现调试
 * @author: T.LM
 * @date: 2023-03-01 22:20
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class NacosClientService {

    private final DiscoveryClient discoveryClient;

    public List<ServiceInstance> getNacosClientInstance(String serviceId) {
        log.info("client desc: {}", discoveryClient.description());
        log.info("client service id: {}", serviceId);
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
        log.info(JSON.toJSONString(instances, JSONWriter.Feature.PrettyFormat));
        return instances;
    }
}
