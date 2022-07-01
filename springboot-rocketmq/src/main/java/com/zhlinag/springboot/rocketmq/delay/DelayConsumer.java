package com.zhlinag.springboot.rocketmq.delay;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * ClassName: OnewayConsumer
 * Function:
 * Date: 2022年04月19 17:37:19
 *
 * @author 张良 E-mail:zhangliang01@jingyougroup.com
 * @version V1.0.0
 */
@Slf4j
@Component
@RocketMQMessageListener(
        topic = "delay-topic",
        consumerGroup = "springboot-rocketmq-delay"
)
public class DelayConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.info("接收到延时消息: [{}]", message);
    }
}
