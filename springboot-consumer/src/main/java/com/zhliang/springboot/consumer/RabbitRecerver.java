package com.zhliang.springboot.consumer;

import com.rabbitmq.client.Channel;
import com.zhliang.springboot.produce.entity.Order;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @Author: colin
 * @Date: 2019/9/24 09:56
 * @Description:
 * @Version: V1.0
 */
@Component
public class RabbitRecerver {


    //   @RabbitListener(bindings = @QueueBinding(
    //			value = @Queue(value = "order-queue",durable = "true"),
    //			exchange = @Exchange(name = "order-exchange",durable = "true",type = "topic"),
    //			key = "order.*"
    //			)
    //	)
    //	@RabbitHandler
    //	public void onOrderMessage(@Payload Order order, @Headers Map<String, Object> headers, Channel channel) throws
    // IOException {
    //		//消费者开始操作
    //		System.out.println("======收到消息，开始消费======");
    //		System.out.println("订单ID："+order.getId());
    //
    //		//ACK
    //		channel.basicAck((Long)headers.get(AmqpHeaders.DELIVERY_TAG), false);
    //	}

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.listener.order.queue.name}",
                    durable = "${spring.rabbitmq.listener.order.queue.durable}"),
            exchange = @Exchange(value = "${spring.rabbitmq.listener.order.exchange.name}",
                    durable = "${spring.rabbitmq.listener.order.exchange.durable}",
                    type = "${spring.rabbitmq.listener.order.exchange.type}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.order.exchange" +
                            ".ignoreDeclarationExceptions}"),
            key = "${spring.rabbitmq.listener.order.key}"
    )
    )
    @RabbitHandler
    public void onOrderMessage(@Payload Order order, Channel channel, @Headers Map<String, Object> headers) throws IOException {
        System.err.println("------------------------------------------");
        System.out.println("${spring.rabbitmq.listener.order.key}");
        System.err.println("消费端order：" + order.getId());
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        channel.basicAck(deliveryTag, false);
    }
}
