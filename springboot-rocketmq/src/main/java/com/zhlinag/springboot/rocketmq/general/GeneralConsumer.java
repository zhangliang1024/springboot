package com.zhlinag.springboot.rocketmq.general;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
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
        selectorExpression = "*",                 // 指定TAG 支持表达式  默认：*
        messageModel = MessageModel.CLUSTERING,   // 消费模式：集群 广播  默认：集群
        consumerGroup = "springboot-rocketmq"     // 消费者组
)
public class GeneralConsumer implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {

    /**
     * 1. 集群消费模式中：同一个topic下，相同的consumerGroup中只会有一个Consumer收到消息，进行消费
     */
    @Override
    public void onMessage(String message) {
        log.info("接收到消息: [{}]", message);
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        // 设置最大重试次数
        consumer.setMaxReconsumeTimes(5);
        // 如下，设置其它consumer相关属性
        consumer.setPullBatchSize(16);

        // 提高 Consumer 的消费能力
        // 1. 提高消费并行度：增加队列数和消费者数量，提高单个消费者的并行消费线程
        consumer.setConsumeThreadMax(20);
        // 2. 批处理消费，这样一次能拿到多条消息
        consumer.setConsumeMessageBatchMaxSize(1000);
        // 3. TODO 跳过非核心消息
        // 当负载很重的时候，为了保住那些核心的消息，跳过非核心消息。
    }
}
