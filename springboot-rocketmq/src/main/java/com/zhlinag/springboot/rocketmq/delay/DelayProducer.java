package com.zhlinag.springboot.rocketmq.delay;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ClassName: OnewayProducer
 * Function:
 * Date: 2022年04月19 17:37:04
 *
 * @author 张良 E-mail:zhangliang01@jingyougroup.com
 * @version V1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DelayProducer {

    private final RocketMQTemplate rocketMQTemplate;

    /**
     * 延时消息只支持固定的等级：
     * 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     * 从1s-2h 固定18个等级，消息消费失败会进入延时消息队列
     */
    public void sendDelayMessage(){
        String mesage = "延时消息:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Message<String> build = MessageBuilder.withPayload(mesage).build();
        // 延时等级4  延时30s
        this.rocketMQTemplate.syncSend("delay-topic",build,2000,4);
    }
}
