package com.zhlinag.springboot.rocketmq.general;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

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
public class GeneralProducer {

    private final RocketMQTemplate rocketMQTemplate;

    public void sendGeneralMessage() {
        SendResult result = this.rocketMQTemplate.syncSend("general-topic", "普通消费");
        log.info("Send MQ result: {}", result);
    }
}
