package com.zhliang.springbootrabbitmq.more2more;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: colin
 * @Date: 2019/8/6 11:15
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Component
public class Producer2 {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sned(String msg){
        String message = msg + new Date();
        log.info("【producer2】: {}",message);
        rabbitTemplate.convertAndSend("helloQueue",message);
    }

}
