package com.zhliang.springboot.rabbitmq.producer.domain;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: colin
 * @Date: 2019/8/6 19:31
 * @Description:
 * @Version: V1.0
 */
@Configuration
public class UserQueue {

    @Bean
    public Queue user(){
        return new Queue("user");
    }
}
