package com.zhliang.springboot.mq.rocket.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.Scanner;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.mq.rocket.producer
 * @类描述：
 * @创建人：colin
 * @创建时间：2020/1/13 10:29
 * @version：V1.0
 */
public class Producer_1 {

    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("my-group");
        producer.setNamesrvAddr("localhost:9876");
        producer.setInstanceName("rmq-instance");
        producer.start();
        try {
            Message message = new Message("demo-topic", "demo-tag", "这是一条测试消息".getBytes());
            producer.send(message);

            //注意：下面代码用户手动输入发送消息内容
            while (true) {
                String text = new Scanner(System.in).next();
                Message msg = new Message("demo-topic", // topic
                        "demo-tag", // tag
                        text.getBytes() // body
                );
                SendResult sendResult = producer.send(msg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }
}
