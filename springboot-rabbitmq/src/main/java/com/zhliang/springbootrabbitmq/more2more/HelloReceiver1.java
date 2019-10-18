package com.zhliang.springbootrabbitmq.more2more;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: colin
 * @Date: 2019/8/6 11:18
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Component
@RabbitListener(queues = "helloQueue")
public class HelloReceiver1 {

    @RabbitHandler
    public void process(String message){
        log.info("【receiver1】: {}",message);
    }
}
