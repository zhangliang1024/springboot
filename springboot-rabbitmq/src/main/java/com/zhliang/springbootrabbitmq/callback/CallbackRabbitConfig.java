package com.zhliang.springbootrabbitmq.callback;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Author: colin
 * @Date: 2019/8/6 15:59
 * @Description:
 * @Version: V1.0
 */
@Configuration
public class CallbackRabbitConfig {

    @Value("${spring.rabbitmq.address}")
    private String address;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;
    @Value("${spring.rabbitmq.publisher-confirms}")
    private boolean publisherConfirms;


    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setAddresses(address);
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setVirtualHost(virtualHost);
        //如果要进行消息回调，这里必须要设置为 true
        factory.setPublisherConfirms(publisherConfirms);
        return  factory;
    }

    /**
     * 因为要设置回调类，所以应是 prototype类型；如果singleton类型，则回调类为最后一次设置
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate(){
        return new RabbitTemplate(connectionFactory());
    }



}
