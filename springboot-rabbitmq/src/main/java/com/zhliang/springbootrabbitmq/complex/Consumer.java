package com.zhliang.springbootrabbitmq.complex;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: colin
 * @Date: 2019/8/2 20:20
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Component
@RabbitListener(queues = "helloQueue")
public class Consumer {

    @RabbitHandler
    public void process(String message){
        log.info("【Consumer】 :" + message);
    }
}
