package com.zhlinag.springboot.rocketmq.transaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * ClassName: TransactionProducer
 * Function:
 * Date: 2022年04月19 17:56:57
 *
 * @author 张良 E-mail:zhangliang01@jingyougroup.com
 * @version V1.0.0
 */
@Slf4j
//@Service
@RequiredArgsConstructor
public class TransactionProducer {

    private final RocketMQTemplate rocketMQTemplate;

    public void sendTransactionMessage() {
        int i = ThreadLocalRandom.current().nextInt(1000);
        Map<String, String> map = new HashMap<>(2);
        map.put("key", "key" + i);
        map.put("name", "事务消息");
        map.put("desc", "事务消息" + i);
        Message<Map<String, String>> message = MessageBuilder.withPayload(map).setHeader("key", map.get("key")).build();
        rocketMQTemplate.sendMessageInTransaction("tx_topic", message, i);
    }

}
