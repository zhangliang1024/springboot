package com.zhliang.springbootrabbitmq.ack;


import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: colin
 * @Date: 2019/8/10 18:37
 * @Description:  ACK机制：描述了 Consumer与Broker确认消息的方式(时机),比如消息被Consumer接受之后,Consumer将在何时确认消息。
 *                         对Broker而言，只有接收到ACK指令，才会认为消息被正确的接受或处理成功了。
 *                         ACK是一种担保机制
 * @Version: V1.0
 */
@Slf4j
public class AckProducer {

   public static ConnectionFactory factory(){
       ConnectionFactory factory = new ConnectionFactory();
       factory.setHost("111.231.202.87");
       factory.setPort(AMQP.PROTOCOL.PORT);
       factory.setVirtualHost("/");
       factory.setUsername("guest");
       factory.setPassword("guest");
       return factory;
   }

   public static Connection connection() throws IOException, TimeoutException{
       ConnectionFactory factory = factory();
       Connection connection = factory.newConnection();
       return connection;
   }

    public static Channel init() throws IOException, TimeoutException{

        Connection connection = connection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("direct.exchange", BuiltinExchangeType.DIRECT);
        channel.queueDeclare("direct.queue",true,false,false,null);
        // queue exchange key
        channel.queueBind("direct.queue","direct.exchange","direct");
        return channel;
    }


    public static void main(String[] args) throws Exception {
        // 发送消息
        //send();
        //sendCon();    //Confirm机制
        //sendTX();     //事务机制

        Thread.sleep(2000);

         //接收消息
        //autoProcess(); //自动应答
        process();     //手动应答
        //rejectProcess(); //手动拒绝
        //recoverProcess();  //重新投递
   }


    // 重新投递
    public static void recoverProcess() throws Exception {
        Connection connection = connection();
        Channel channel = init();

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                log.info("[MQ ACK 接收消息] body: {}",message);
                if(message.contains("3")){
                    channel.basicRecover(false);
                }else {
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };
        /**设置消息自动应答模式：重新投递
         *    重新投递: 将消费者还没有处理的所有消息都重新放入到队列中，而不是将某一条消息放入对列中
         *    与basicReject不同的是，重新投递可以指定投递的消息是否允许当前消费者消息
         *
         *    true:
         *    false:
         *
         */
        channel.basicConsume("direct.queue",false,consumer);
        Thread.sleep(100000);
    }

    //手动拒绝
    public static void rejectProcess() throws Exception {
        Connection connection = connection();
        Channel channel = init();

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                log.info("[MQ ACK 接收消息] body: {}",message);
                if(message.contains("3")){
                    channel.basicReject(envelope.getDeliveryTag(),true);
                    //抛异常，消息不会丢。会在队列中，但不重新投递。异常后面的消息不会消费，会在队列中堆积
                    //throw new RuntimeException();
                }else {
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };
        /**设置消息自动应答模式：手动拒绝
         * true:  表示将消息重新放入队列中
         * false: 表示直接从队列中删除
         *        此时 和  channel.basicAck(envelope.getDeliveryTag(),false) 效果一样
         */
        channel.basicConsume("direct.queue",false,consumer);
        Thread.sleep(100000);
    }

    public static void process() throws Exception {
        Connection connection = connection();
        Channel channel = init();

        /**指定每次取一条消息进行消费
         * 此时如果没有应答的话，消费者将不再继续获取
         * 消费者运行结束的时候又回到原来的状态Ready=5, Unacked=0, Total=5
         */

        channel.basicQos(1);
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                log.info("[MQ ACK 接收消息] body: {}",message);
                //抛异常，消息不会丢。会在队列中，但不重新投递。异常后消息不会消费，会在队列中堆积
                //throw new RuntimeException();
                //if(message.contains("3"))
                //    //消息nack 会重回到队列顶部
                //    channel.basicNack(envelope.getDeliveryTag(),false,true);
                //else
                channel.basicAck(envelope.getDeliveryTag(),false);  //false :表示一次只签收一条消息 不做批量签收

            }
        };
        /**设置消息自动应答模式：手动应答
         * 当消费者收到消息在合适的时候来显示的进行确认，接收到了该消息
         * RabbitMQ可以从队列中删除该消息了
         *
         * ★ 手动应答：如果忘记ack的话，消费者任然可以获取所以消息
         *    不过此时Unacked和Total一直都是5，Ready=0, Unacked=5, Total=5，
         *    直到消费者运行结束，Ready=5, Unacked=0, Total=5
         */
        channel.basicConsume("direct.queue",false,consumer);
        Thread.sleep(100000);
    }


    public static void autoProcess() throws Exception {
        Connection connection = connection();
        Channel channel = init();

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                //if(message.contains("3")){
                //    //异常消息会丢失,不会重回队列
                //    throw new RuntimeException();
                //}
                log.info("[MQ ACK 接收消息] body: {}",message);
            }
        };
        /**设置消息自动应答模式：自动应答
         * 因为没有指定消费者一次获取消息的条数，所以队列会把消息一下都推送到消费者端。
         * 当消息从队列被推出的那一刻就表示：已经对消息进行自动确认了，消息就会从队列中删除
         */
        channel.basicConsume("direct.queue",true,consumer);
        Thread.sleep(100000);
    }

    /** 生产者确认
     *  当生产者发布消息到RabbitMQ中，生产者需要知道是否真的已经发送到了RabbitMQ中，需要RabbitMQ告诉生产者
     *  1. Confirm机制
     *     channel.confirmSelect();
     *     channel.waitForConfirms();
     *
     *  2. 事务机制
     *     channel.txSelect();
     *     channel.txCommit();
     *     channel.txRollback();
     *
     *  ★ 事务机制是非常耗费性能的，最好使用Confirm机制，Confirm机制相比事务机制性能上要很多
     */

    public static void sendCon() throws Exception {
        Connection connection = connection();
        Channel channel = init();

        channel.confirmSelect();

        for (int i = 0; i < 5; i++) {
            channel.basicPublish("direct.exchange","direct",null,("hello rabbitmq : " + i).getBytes("UTF-8"));
            if(i == 3){
                int a = 1/0;
            }
        }
        log.info("[MQ ACK 消息发送完毕]");

        boolean bool = channel.waitForConfirms();
        log.info("[MQ Confirm机制 消息发送到RabbitMQ结果] : {}",bool);

        channel.close();
        connection.close();
    }

    public static void sendTX() throws Exception {
        Connection connection = connection();
        Channel channel = init();
        try{
            channel.txSelect();
            for (int i = 0; i < 5; i++) {
                channel.basicPublish("direct.exchange","direct",null,("hello rabbitmq : " + i).getBytes("UTF-8"));
            }
            log.info("[MQ ACK 消息发送完毕]");
            channel.txCommit();
        }catch (Exception e){
            log.info("[MQ ACK 事务机制,异常回滚]");
            channel.txRollback();
        }
        channel.close();
        connection.close();
    }

    public static void send() throws Exception {
        Connection connection = connection();
        Channel channel = init();

        for (int i = 0; i < 5; i++) {
            channel.basicPublish("direct.exchange","direct",null,("hello rabbitmq : " + i).getBytes("UTF-8"));
        }
        log.info("[MQ ACK 消息发送完毕]");
        channel.close();
        connection.close();
    }

}
