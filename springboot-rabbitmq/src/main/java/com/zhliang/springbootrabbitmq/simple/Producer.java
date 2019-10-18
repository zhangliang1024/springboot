package com.zhliang.springbootrabbitmq.simple;

import com.zhliang.springbootrabbitmq.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: colin
 * @Date: 2019/8/1 17:42
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Component
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String msg){
        String sendMsg = msg + new Date();
        log.info("【发送消息请求】：{}",sendMsg);
        this.rabbitTemplate.convertAndSend(RabbitConfig.queue_name,sendMsg);
    }

}
