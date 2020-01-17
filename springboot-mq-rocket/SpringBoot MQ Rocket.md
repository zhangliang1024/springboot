# SpringBoot RocketMQ 使用

### Wins RocketMQ 启动
```sbtshell
执行如下命令如下图显示：(启动后cmd窗口不能关闭)	在MQ/bin目录下
1.启动mqnamesrv:  start mqnamesrv.cmd		
2.启动mqbroker：  mqbroker -n localhost:9876 autoCreateTopicEnable=true		

```

### Springboot RocketMQ 依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.apache.rocketmq</groupId>
    <artifactId>rocketmq-client</artifactId>    
    <version>4.3.0</version>
</dependency>

```

### Reference Documentation
    For further reference, please consider the following sections:

### Springboot 集成RocketMQ
* [RocketMQ在windows环境下的搭建安装](https://blog.csdn.net/yanwendonge/article/details/88658978)
* [SpringBoot集成RocketMQ](https://www.cnblogs.com/wadmwz/p/10689972.html)

### RocketMQ原理入门篇
* [RocketMQ入门篇](https://segmentfault.com/a/1190000017841402)
* [Rocketmq原理&最佳实践](https://www.jianshu.com/p/2838890f3284)

