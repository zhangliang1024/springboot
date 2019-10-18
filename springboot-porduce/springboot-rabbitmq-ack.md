# Springboot-RabbitMQ 处理消息丢失
> [RabbitMQ消息可靠性投递解决方案 - 基于SpringBoot实现](https://www.imooc.com/article/49814)
```properties
在使用 RabbitMQ 的时候，作为消息发送方希望杜绝任何消息丢失或者投递失败场景。RabbitMQ 为我们提供了两个选项用来控制消息的投递可靠性模式。

rabbitmq 整个消息投递的路径为：
producer->rabbitmq broker cluster->exchange->queue->consumer

message 从 producer 到 rabbitmq broker cluster 则会返回一个 confirmCallback 。
message 从 exchange->queue 投递失败则会返回一个 returnCallback 。我们将利用这两个 callback 控制消息的最终一致性和部分纠错能力。


链接：https://www.imooc.com/article/68507
```

### 问题
> - 为什么要进行消息确认 ？
>  - 防止消息丢失
> - rabbitMQ的消息确认机制 是什么 ？
>  - 事务机制 (影响性能)
>  - 消息确认confirm机制
> - 发送方如何确认消息发送成功，什么样才算发送成功 ？
>  - 消息可以路由，并发送到队列成功
>  - 对于持久化队列：消息写入磁盘，镜像队列则所有镜像都接受到了消息
> - 消费方如何告知rabbitMQ 消息消费成功或失败 ？
>  - 确认模式：根据消费方式不同不同
>    - 自动确认：会在消息发送给消费者后立即确认。
>    - 手动确认：需要消费者调用 ACK NACK REJECT 几种方式进行确认

### 解決方案
```properties
一、消息持久化
1. Exchange 设置持久化：durable = true
   new TopicExchange("amq.topic"); 创建的Exchange 默认是持久化的
2. Queue 设置持久化
   new Queue("abc.queue");  创建的Queue 默认是持久化的
3. Message 持久化发送
   发送消息 设置默认发送模式：deliveryMode = 2 代表持久化发送

二、ACK确认机制
1. 消息发送确认
   ConfirmCallback  只确认消息是否正确到达Exchange中
   ReturnCallback   消息没有正确到达队列触发回调，如果正确到达队列不执行。
   [return-callback="returnCallback" mandatory="true"同时设置了才有效]
2. 消息接收确认
   取消自动ACK，改为手动ACK(确认消息)

三、设置集群镜像模式
    做好MQ高可用机制

四、消息补偿机制
    消息落地DB, 定时任务去执行发送失败的消息
    多次失败的消息，需人工手动触发执行

```

### RabbitMQ confirm机制 return机制
```properties
1. 消息路由正确并发送到queue confirm被回调：ack=true
2. 消息路由错误 没有发送到queue confirm被回调：ack=false
3. 消息路由正确 queue错误，消息没有发送到queue
   - confirm被回调：ack=true
   - return被回调   replyText:NO_ROUTE (需要设置mandatory=true 否则不回调消息就丢了)
4. 消息路由错误 queue错误，消息没有发送到queue
   - confirm被回调：ack=false
```
### 相关问题记录

* [消息发送成功但confirm返回ack=false](https://blog.csdn.net/dh554112075/article/details/90137869)
* [RabbitMQ(四)消息确认(发送确认,接收确认)](https://blog.csdn.net/qq315737546/article/details/54176560)
* [RabbitMQ 消息如何保证100%的投递成功](https://www.imooc.com/article/257955)
* [RabbitMQ的消息丢失解决方案](https://www.imooc.com/article/272617)
  [demo实现](https://github.com/wuzhong290/rabbitmq)
