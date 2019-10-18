package com.zhliang.springboot.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Service;

/**
 * @Author: colin
 * @Date: 2019/9/24 17:50
 * @Description:
 * @Version: V1.0
 */

@Service("receiveConfirmTestListener")
public class ReceiveConfirmTestListener implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try{
            System.out.println("consumer--:"+message.getMessageProperties()+":"+new String(message.getBody()));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch(Exception e){
            e.printStackTrace();
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
        }
    }
}
