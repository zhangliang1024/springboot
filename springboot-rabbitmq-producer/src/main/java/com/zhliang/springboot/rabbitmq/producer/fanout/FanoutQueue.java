package com.zhliang.springboot.rabbitmq.producer.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: colin
 * @Date: 2019/8/9 10:46
 * @Description:
 * @Version: V1.0
 */
@Configuration
public class FanoutQueue {

    @Bean
    public Queue queueA(){
        return new Queue("fanout.A");
    }
    @Bean
    public Queue queueB(){
        return new Queue("fanout.B");
    }
    @Bean
    public Queue queueC(){
        return new Queue("fanout.C");
    }


    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout.exchange");
    }

    @Bean
    public Binding bindingExchangeA(){
        return BindingBuilder.bind(queueA()).to(fanoutExchange());
    }
    @Bean
    public Binding bindingExchangeB(){
        return BindingBuilder.bind(queueB()).to(fanoutExchange());
    }
    @Bean
    public Binding bindingExchangeC(){
        return BindingBuilder.bind(queueC()).to(fanoutExchange());
    }
}
