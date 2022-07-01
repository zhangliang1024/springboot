package com.zhlinag.springboot.rocketmq.async;

import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

/**
 * ClassName: AsyncProducer
 * Function:
 * Date: 2022年04月19 16:57:00
 *
 * @author 张良 E-mail:zhangliang01@jingyougroup.com
 * @version V1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AsyncProducer {

    private final RocketMQTemplate rocketMQTemplate;

    /**
     * 异步消息发送，指定回调方法
     * 发送线程不阻塞，消息发送成功或失败的回调任务在一个新的线程中执行
     */
    public void snedAsyncMessage() {
        this.rocketMQTemplate.asyncSend("async-topic", "异步消息", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("Async Send Success: [{}]", JSON.toJSONString(sendResult));
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("Async Send Error: [{}]", throwable.getMessage());
            }
        });
    }

}
