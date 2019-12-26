package com.zhliang.springboot.redis.msg.producer;

import com.zhliang.springboot.redis.msg.MessageEntity;
import com.zhliang.springboot.redis.msg.queue.MessageConsumerService;
import com.zhliang.springboot.redis.msg.queue.MessageProducerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqProducerApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testSubscribe() {
        String channel = "topic";
        redisTemplate.convertAndSend(channel, "hello world");
        redisTemplate.convertAndSend(channel, new Date(System.currentTimeMillis()));
        redisTemplate.convertAndSend(channel, new MessageEntity("1", "object"));
    }


    @Autowired
    private MessageProducerService producer;

    @Autowired
    private MessageConsumerService consumer;

    @Test
    public void testQueue() {
        consumer.start();
        producer.sendMeassage(new MessageEntity("1", "aaaa"));
        producer.sendMeassage(new MessageEntity("2", "bbbb"));

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        consumer.interrupt();
    }
}
