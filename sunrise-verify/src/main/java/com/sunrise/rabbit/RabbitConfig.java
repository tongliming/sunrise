package com.sunrise.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: sunrise
 * @description:
 * @author: T.LM
 * @date: 2023-03-05 15:08
 **/
@Configuration
public class RabbitConfig {
    /**
     * 使用 ExchangeBuilder 创建交换机
     *
     */
    @Bean("bootExchange")
    public Exchange bootExchange() {
        return ExchangeBuilder.directExchange("bootExchange").durable(true).build();
    }

    /**
     * 创建队列
     */
    @Bean("bootQueue")
    public Queue bootQueue() {
        return QueueBuilder.durable("bootQueue").build();
    }

    /**
     * 创建队列和交换机的绑定关系
     */
    @Bean("bootBinding")
    public Binding bootBinding() {
        return BindingBuilder.bind(bootQueue()).to(bootExchange()).with("boot.key").and(null);
    }

}
