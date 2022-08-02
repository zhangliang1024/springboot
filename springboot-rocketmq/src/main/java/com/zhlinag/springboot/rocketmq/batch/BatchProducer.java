package com.zhlinag.springboot.rocketmq.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: GeneralProducer
 * Function:
 * Date: 2022年04月19 16:32:48
 *
 * @author 张良 E-mail:zhangliang01@jingyougroup.com
 * @version V1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BatchProducer {

    private final RocketMQTemplate rocketMQTemplate;

    public void sendBatchMessage() {
        List<String> language = Arrays.asList("java", "python", "golang");
        List<Message<String>> messages = language.stream().map(content -> MessageBuilder.withPayload(content).build()).collect(Collectors.toList());
        SendResult result = this.rocketMQTemplate.syncSend("batch-topic", messages);
        log.info("Send MQ result: {}", result);
    }
}
