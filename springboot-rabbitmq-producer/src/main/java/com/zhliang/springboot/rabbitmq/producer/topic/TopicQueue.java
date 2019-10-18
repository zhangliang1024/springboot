package com.zhliang.springboot.rabbitmq.producer.topic;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: colin
 * @Date: 2019/8/7 10:47
 * @Description:
 * @Version: V1.0
 */
@Configuration
public class TopicQueue {

    @Bean(name = "message")
    public Queue topicQueue(){
        return new Queue("topic.message");
    }

    @Bean(name = "messages")
    public Queue topicQueues(){
        return new Queue("topic.messages");
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("exchange");
    }

    @Bean
    public Binding bindingExchangeMessage(){
        return BindingBuilder.bind(topicQueue()).to(exchange()).with("topic.message");
    }

    @Bean
    public Binding bindingExchangeMessages(){
        return BindingBuilder.bind(topicQueues()).to(exchange()).with("topic.#");
    }





}
