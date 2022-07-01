package com.zhlinag.springboot.rocketmq.sample;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * ClassName: BaseConsumerListener
 * Function:
 * Date: 2022年04月24 16:03:41
 *
 * @author 张良 E-mail:zhangliang01@jingyougroup.com
 * @version V1.0.0
 */
@Slf4j
@Service
@RocketMQMessageListener(
        topic = MqConstants.TOPIC_PREFFIX + MqConstants.Topic.TOPICSTRING, //topic
        consumerGroup = MqConstants.GROUP_PREFFIX + MqConstants.Topic.TOPICSTRING, //分组规则，Group-"topic命名"
        //下边可以去掉，都使用的是默认值
        consumeMode = ConsumeMode.CONCURRENTLY, //默认值，并发接收模式
        messageModel = MessageModel.CLUSTERING, //默认值，集群模式
        selectorType = SelectorType.TAG, // 默认值，标签
        selectorExpression = "*" //默认值，匹配该topic下所有tag
)
public class BaseConsumerListener extends BaseAbstractConsumer<String> implements RocketMQListener<MessageExt> {


    @Override
    public void onMessage(MessageExt message) {
        log.info("消费者接收：Topic：{}", message.getTopic());
        if (!isVerify(message, String.class)) {
            return;
        }
        // 执行MQ 消息公共处理
        todo();
    }

    /**
     * 具体业务处理
     */
    @Override
    protected void handle() {
        // TOOD 业务处理
        log.info("业务处理：消息内容：{}", body);
    }

}
