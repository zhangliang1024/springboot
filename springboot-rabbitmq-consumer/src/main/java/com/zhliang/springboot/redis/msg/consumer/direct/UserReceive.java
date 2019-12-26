package com.zhliang.springboot.redis.msg.consumer.direct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @Author: colin
 * @Date: 2019/8/6 19:16
 * @Description:
 * @Version: V1.0
 */
@Slf4j
//@Component
public class UserReceive {


    @RabbitHandler
    @RabbitListener(queues = "user")
    public void process(User user){
        log.info("[Direct 模式接受实体对象消息]：{}",user);
    }
}
