package com.zhlinag.springboot.rocketmq.transaction;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * ClassName: TransactionConsumer
 * Function:
 * Date: 2022年04月19 18:01:30
 *
 * @author 张良 E-mail:zhangliang01@jingyougroup.com
 * @version V1.0.0
 *
 * 事务消息只保证了：本地事务和消息发送的原子性
 * 若消息端消费失败：建议记录异常+人共处理
 */
@Slf4j
//@Component
//@RocketMQMessageListener(
//        topic = "tx-topic",
//        consumerGroup = "springboot-rocketmq-tx"
//)
public class TransactionConsumer implements RocketMQListener<Map<String,String>> {


    @Override
    public void onMessage(Map<String, String> message) {
        log.info("接收到事务消息: [{}]", message);
    }
}
