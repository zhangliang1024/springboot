package com.zhliang.springboot.mq.rocket.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.mq.rocket.producer
 * @类描述：
 * @创建人：colin
 * @创建时间：2020/1/10 18:22
 * @version：V1.0
 */
public class MqProducer {

    public static void main(String[] args) throws Exception {
        // 构造Producer
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
        producer.setNamesrvAddr("192.168.8.106:9876");
        // 初始化Producer，整个应用生命周期内，只需要初始化1次
        producer.start();
        for (int i = 0; i < 100; i++) {
            Message msg = new Message("TopicTest", "TagA", ("Hello RocketMQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg);
            System.out.println(sendResult);
        }
        producer.shutdown();
    }

}
