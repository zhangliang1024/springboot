package com.zhliang.springboot.mq.rocket.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.mq.rocket.consumer
 * @类描述：
 * @创建人：colin
 * @创建时间：2020/1/13 10:31
 * @version：V1.0
 */
public class Consumer_1 {

    public static void main(String[] args) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("my-group");

        consumer.setNamesrvAddr("192.168.8.103:9876");
        consumer.setInstanceName("rmq-instance");
        try {
            consumer.subscribe("demo-topic", "demo-tag");
            consumer.registerMessageListener(new MessageListenerConcurrently() {

                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    for (MessageExt msg : msgs) {
                        System.out.println(new String(msg.getBody()));
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
            System.out.println("Consumer Started.");
        }
        catch (MQClientException e) {
            e.printStackTrace();
        }
    }
}
