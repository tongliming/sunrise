package com.sunrise.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @program: sunrise
 * @description:
 * @author: T.LM
 * @date: 2023-03-05 15:41
 **/
@Service
public class RabbitService {
    @RabbitListener(queues = {"bootQueue"})
    public void rabbitListener(Message message) {
        System.out.println("收到消息===" + message);
    }

}
