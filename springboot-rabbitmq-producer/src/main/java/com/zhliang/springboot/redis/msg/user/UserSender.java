package com.zhliang.springboot.redis.msg.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @Author: colin
 * @Date: 2019/8/10 14:15
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Component
public class UserSender {

    @Autowired
    private AmqpTemplate template;

    public void send(){
        User user = new User();
        user.setUsername("zhsan");
        user.setPassword("hello");
        template.convertAndSend("user.exchange","user.exchange",user);
    }

    public void sendByByte() throws Exception{
        User user = new User();
        user.setUsername("zhsan");
        user.setPassword("hello");
        //把对象转换为字节码传输
        byte[] bytes = getBytesFromObj(user);
        log.info("[MQ 发送序列号字节码]");
        template.convertAndSend("user.exchange","user.exchange",bytes);
    }

    /**
     * 对象转换为字节码
     */
    public byte[]  getBytesFromObj(Serializable obj) throws Exception{
        if(obj == null)  return null;
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bo);
        out.writeObject(bo);
        return bo.toByteArray();
    }
}
