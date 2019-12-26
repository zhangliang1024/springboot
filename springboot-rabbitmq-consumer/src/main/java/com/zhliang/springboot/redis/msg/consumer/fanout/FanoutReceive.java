package com.zhliang.springboot.redis.msg.consumer.fanout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: colin
 * @Date: 2019/8/9 10:49
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Component
public class FanoutReceive {


    @RabbitHandler
    @RabbitListener(queues = "fanout.A")
    public void processA(String msg){
        log.info("[MQ 测试 fanout接受参数 A]:{}",msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "fanout.B")
    public void processB(String msg){
        log.info("[MQ 测试 fanout接受参数 B]:{}",msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "fanout.C")
    public void processC(String msg){
        log.info("[MQ 测试 fanout接受参数 C]:{}",msg);
    }

}
