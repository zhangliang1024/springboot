package com.zhliang.springbootrabbitmq.complex;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: colin
 * @Date: 2019/8/2 20:35
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Component
public class OneToManyProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String msg){
        String sendMsg = msg + new Date();
        log.info("【生产者发送消息】:{}",sendMsg);
        this.rabbitTemplate.convertAndSend("helloQueue",sendMsg);
    }

}
