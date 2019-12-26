package com.zhliang.springboot.redis.msg.queue;

import com.zhliang.springboot.redis.msg.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.redis.msg.queue
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/9 15:39
 * @version：V1.0
 */
@Service
public class MessageConsumerService extends Thread {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private volatile boolean flag = true;

    @Value("${redis.queue.key}")
    private String queueKey;

    @Value("${redis.queue.pop.timeout}")
    private Long popTimeout;

    @Override
    public void run() {
        try {
            Object message;
            while(flag && !Thread.currentThread().isInterrupted()) {
                message = (Object)redisTemplate.opsForList().rightPop(queueKey, popTimeout, TimeUnit.SECONDS);
                System.out.println("接收到了" + message);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}
