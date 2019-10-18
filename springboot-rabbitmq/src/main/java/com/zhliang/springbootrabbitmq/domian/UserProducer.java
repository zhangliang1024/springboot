package com.zhliang.springbootrabbitmq.domian;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: colin
 * @Date: 2019/8/6 14:18
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Component
public class UserProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @GetMapping("domain/send")
    public void sendPO(){
        User user = new User();
        user.setName("zhliang");
        user.setPass("password");
        log.info("【MQ测试实体对象】：{}",user);
        rabbitTemplate.convertAndSend("user",user);
    }
}
