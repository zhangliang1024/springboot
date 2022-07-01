package com.zhlinag.springboot.rocketmq.orderly;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * ClassName: OrderlyConsumer
 * Function:
 * Date: 2022年04月19 16:49:29
 *
 * @author 张良 E-mail:zhangliang01@jingyougroup.com
 * @version V1.0.0
 */
@Slf4j
@Component
@RocketMQMessageListener(
        topic = "orderly-topic",
        consumeMode = ConsumeMode.ORDERLY,  //指定为顺序消费
        consumerGroup = "springboot-rocketmq-orderly"
)
public class OrderlyConsumer implements RocketMQListener<String> {

    /**
     * 接收到的消息也是顺序的
     */
    @Override
    public void onMessage(String message) {
        log.info("接收到消息: [{}]", message);
    }
}
