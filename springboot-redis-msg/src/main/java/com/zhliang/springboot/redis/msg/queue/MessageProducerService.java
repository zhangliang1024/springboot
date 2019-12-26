package com.zhliang.springboot.redis.msg.queue;

import com.zhliang.springboot.redis.msg.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.redis.msg.queue
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/9 15:42
 * @version：V1.0
 */
@Service
public class MessageProducerService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${redis.queue.key}")
    private String queueKey;

    public Long sendMeassage(MessageEntity message) {
        System.out.println("发送了" + message);
        return redisTemplate.opsForList().leftPush(queueKey, message);
    }

}
