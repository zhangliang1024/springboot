package com.zhliang.springboot.mq.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.mq.kafka
 * @类描述：
 * @创建人：colin
 * @创建时间：2020/1/15 15:16
 * @version：V1.0
 */
@Component
public class Consumer {

    @KafkaListener(topics = {"test"})
    public void listen(ConsumerRecord<?, ?> record){

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();
            System.err.println("record: ---->"+record);
            System.out.println("message: ---->"+message);

        }

    }
}
