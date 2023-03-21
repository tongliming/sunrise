package com.sunrise.rabbit;

import com.alibaba.nacos.shaded.com.google.protobuf.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: sunrise
 * @description:
 * @author: T.LM
 * @date: 2023-03-05 15:11
 **/
@RestController
@RequestMapping
public class Production {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/rabbit")
    public String product() {
        rabbitTemplate.convertAndSend("bootExchange", "boot.key", "HELLO SPRING BOOT");
        return "success";
    }

//    @RabbitListener(queues = {"bootQueue"})
    public void rabbitListener(Message message) {
        System.out.println("收到消息===" + message);
    }

}
