package com.zhliang.springboot.redis.msg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.redis.msg
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/6 17:57
 * @version：V1.0
 */
public class ConsumerRedisListener implements MessageListener {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        doBusiness(message);
    }

    /**
     * 打印 message body 内容
     * @param message
     */
    public void doBusiness(Message message) {
        Object value = redisTemplate.getValueSerializer().deserialize(message.getBody());
        System.out.println("consumer message: " + value.toString());
    }

}
