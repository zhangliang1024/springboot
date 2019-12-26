package com.zhliang.springboot.redis.msg.user;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: colin
 * @Date: 2019/8/10 14:13
 * @Description:
 * @Version: V1.0
 */
@Configuration
public class UserRabbitConfig {

    /**新建队列 user.queue*/
    @Bean(name = "user.queue")
    public Queue queue(){
        return  new Queue("user.queue");
    }

    /**定义交换器 user.exchange*/
    @Bean(name = "user.exchange")
    public TopicExchange exchange(){
        return new TopicExchange("user.exchange");
    }

    /**交换器与消息队列进行绑定*/
    @Bean
    Binding bindingUserExchange(){
        return BindingBuilder.bind(queue()).to(exchange()).with("user.exchange");
    }
}
