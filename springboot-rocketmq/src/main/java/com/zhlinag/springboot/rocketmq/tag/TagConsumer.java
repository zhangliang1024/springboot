package com.zhlinag.springboot.rocketmq.tag;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * ClassName: GeneralConsumer
 * Function:
 * Date: 2022年04月19 16:35:27
 *
 * @author 张良 E-mail:zhangliang01@jingyougroup.com
 * @version V1.0.0
 */
@Slf4j
@Component
@RocketMQMessageListener(
        topic = "general-topic",                  // 主题
        consumeMode = ConsumeMode.CONCURRENTLY,   // 消费类型 普通 顺序
        selectorExpression = "tag",               // 指定TAG 支持表达式  默认：*
        messageModel = MessageModel.CLUSTERING,   // 消费模式：集群 广播  默认：集群
        consumerGroup = "springboot-rocketmq"     // 消费者组
)
public class TagConsumer implements RocketMQListener<String> {

    /**
     * 1. 集群消费模式中：同一个topic下，相同的consumerGroup中只会有一个Consumer收到消息，进行消费
     */
    @Override
    public void onMessage(String message) {
        log.info("接收到消息: [{}]", message);
    }
}
