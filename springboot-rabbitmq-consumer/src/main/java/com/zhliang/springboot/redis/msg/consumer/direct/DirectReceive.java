package com.zhliang.springboot.redis.msg.consumer.direct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: colin
 * @Date: 2019/8/6 19:16
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Component
public class DirectReceive {


    @RabbitHandler
    @RabbitListener(queues = "queue")
    public void process(String msg){
        log.info("[Direct 模式接受消息]：{}",msg);
    }
}
