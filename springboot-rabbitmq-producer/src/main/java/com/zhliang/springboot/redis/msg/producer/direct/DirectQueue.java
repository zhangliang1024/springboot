package com.zhliang.springboot.redis.msg.producer.direct;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: colin
 * @Date: 2019/8/6 19:16
 * @Description:
 * @Version: V1.0
 */

@Configuration
public class DirectQueue {

    /**
     * 采用直连模式新建队列: 并指定 绑定Key: queue
     * @return
     */
    @Bean
    public Queue queue(){
        return new Queue("queue");
    }
}
