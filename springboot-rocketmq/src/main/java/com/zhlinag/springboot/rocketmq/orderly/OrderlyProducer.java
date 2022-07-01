package com.zhlinag.springboot.rocketmq.orderly;

import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

/**
 * ClassName: OrderlyProducer
 * Function:
 * Date: 2022年04月19 16:49:17
 *
 * @author 张良 E-mail:zhangliang01@jingyougroup.com
 * @version V1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderlyProducer {

    private final RocketMQTemplate rocketMQTemplate;

    public void sendOrderlyMessage() {
        for (int i = 0; i < 10; i++) {
            SendResult result = this.rocketMQTemplate.syncSendOrderly("orderly-topic", "有序消息" + i, "hashkey");
            log.info("Send Orderly Message Result: [{}]", JSON.toJSONString(result));
        }
    }

}
