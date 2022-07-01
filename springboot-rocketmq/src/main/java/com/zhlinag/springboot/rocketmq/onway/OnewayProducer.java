package com.zhlinag.springboot.rocketmq.onway;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

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
public class OnewayProducer {

    private final RocketMQTemplate rocketMQTemplate;

    /**
     * 单向消息 不关心发送的结果
     */
    public void sendOnewayMessage(){
        this.rocketMQTemplate.sendOneWay("oneway-topic","单向消息");
    }
}
