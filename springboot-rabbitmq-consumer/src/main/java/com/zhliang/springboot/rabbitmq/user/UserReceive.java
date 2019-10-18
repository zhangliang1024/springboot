package com.zhliang.springboot.rabbitmq.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

/**
 * @Author: colin
 * @Date: 2019/8/10 14:17
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Component
public class UserReceive {


    @RabbitHandler
    @RabbitListener(queues = "user.queue")
    public void process(User user){
        log.info("[MQ 测试接收对象] user:{}",user);
    }


    @RabbitHandler
    @RabbitListener(queues = "user.queue")
    public void process(byte[] bytes) throws Exception{
        User user = (User)getObjFromBytes(bytes);
        log.info("[MQ 测试接收字节码转换后的对象] user:{}",user);
    }

    public Object getObjFromBytes(byte[] bytes) throws Exception{
        if(bytes == null || bytes.length == 0) return  null;
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        ObjectInputStream oi = new ObjectInputStream(in);
        return oi.readObject();
    }
}
