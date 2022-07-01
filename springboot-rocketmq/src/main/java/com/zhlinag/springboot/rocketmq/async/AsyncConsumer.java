package com.zhlinag.springboot.rocketmq.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * ClassName: AsyncConsumer
 * Function:
 * Date: 2022年04月19 16:57:52
 *
 * @author 张良 E-mail:zhangliang01@jingyougroup.com
 * @version V1.0.0
 */
@Slf4j
@Component
@RocketMQMessageListener(
        topic = "async-topic",
        consumerGroup = "springboot-rocketmq-async"
)
public class AsyncConsumer implements RocketMQListener<String> {


    @Override
    public void onMessage(String message) {
        log.info("接收到消息: [{}]", message);
    }
}
