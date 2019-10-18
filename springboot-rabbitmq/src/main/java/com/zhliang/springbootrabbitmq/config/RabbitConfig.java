package com.zhliang.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @Author: colin
 * @Date: 2019/8/1 16:45
 * @Description:
 * @Version: V1.0
 */
@Configuration
public class RabbitConfig {

    public static String queue_name = "hello";
    @Bean
    public Queue hell(){
        return new Queue("hello");
    }

    @Bean
    public Queue userQueue(){
        return new Queue("user");
    }

    @Bean
    public Queue helloQueue(){
        return new Queue("helloQueue");
    }


    //===========   以下验证  topic Exchange 队列
    @Bean
    public Queue queueMessageA(){
        return new Queue("topic.message.A");
    }
    @Bean
    public Queue queueMessageB(){
        return new Queue("topic.message.B");
    }
    //===========   以上验证  topic Exchange 队列


    //===========   以下验证  fanout Exchange 队列
    @Bean
    public Queue FanoutMessageA(){
        return new Queue("fanout.A");
    }
    @Bean
    public Queue FanoutMessageB(){
        return new Queue("fanout.B");
    }
    @Bean
    public Queue FanoutMessageC(){
        return new Queue("fanout.C");
    }
    //===========   以上验证  fanout Exchange 队列


    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topic.exchange");
    }

    @Order(1)
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout.exchange");
    }


    // ===========  队列topic.message.A与exchange绑定，绑定key为topic.message
    @Bean
    public Binding bindingExchangeMessage() {
        return BindingBuilder.bind(queueMessageA()).to(topicExchange()).with("topic.exchange");
    }

    // ===========  队列topic.message.B与exchange绑定，绑定key为topic.# 模糊匹配
    @Bean
    public Binding bindingExchangeMessage1(){
        return BindingBuilder.bind(queueMessageB()).to(topicExchange()).with("topic.#");
    }


    // ===========  队列fanout.A与fanout.exchange 绑定关系
    @Bean
    public Binding bindingExchangeA(){
        return BindingBuilder.bind(FanoutMessageA()).to(fanoutExchange());
    }
    @Bean
    public Binding bindingExchangeB(){
        return BindingBuilder.bind(FanoutMessageB()).to(fanoutExchange());
    }
    @Bean
    public Binding bindingExchangeC(){
        return BindingBuilder.bind(FanoutMessageC()).to(fanoutExchange());
    }

}
