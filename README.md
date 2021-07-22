# springboot 学习记录
> 记录`springboot `整合个组件在项目中的使用 



## 二、项目目录介绍

| 模块名称 | 模块介绍 | 备注 |
| -------- | -------- | ---- |
| `springboot-actuator`      | springboot 监控端点、策略模式实现      |   |
| `springboot-aop`           | 基于url的幂等方案、幂等方案总结    |   |
| `springboot-api-mideng`    | 基于注解+拦截+TOKEN的幂等方案      |   |
| `springboot-api-ui`        | springboot使用Knife4j代替swagger做接口文档      |   |
| `springboot-async`         | springboot使用自定义线程池处理异常任务      |   |
| `springboot-cache`         | springboot使用cache缓存      |   |
| `springboot-configuration-properties` | springboot中@ConfigurationProperties注解使用      |   |
| `springboot-consumer`      | springboot rabbitmq消费端      |   |
| `springboot-custom-starter` | springboot 自定义starter      |   |
| `springboot-date`           | springboot 基于注解实现全局日期格式化,处理精度丢失问题。Java8 LocalDate      |   |
| `springboot-dingding`       | springboot异常消息钉钉通知      |   |
| `springboot-druid`          | springboot durid的使用      |   |
| `springboot-dynamic-datasource` | springboot动态数据源切换      |   |
| `springboot-ehcache`        | springboot eachche缓存      |   |
| `springboot-event`          | springboot 自定义事件      |   |
| `springboot-file`           | springboot 文件上传      |   |
| `springboot-guava-tomato`   | springboot cache使用 基于tomato的幂等、重复提交处理      |   |
| `springboot-json`           | springboot 【jackson、fastJson】的使用      |   |
| `springboot-kaptcha`        | springboot Google Kaptcha 验证码使用      |   |
| `springboot-leetcode`       | 算法学习      |   |
| `springboot-lock`      | `tools-redis` 实现分布式锁、分布式缓存 |   |
| `springboot-lock-redis`      |  |   |
| `springboot-log`      | `springboot` 日志配置 |   |
| `springboot-login-control`      | `springboot` 并发登录人数控制 |   |
| `springboot-mail`      | `springboot Mail` 发送邮件和附件 |   |
| `springboot-miaosha`      | 基于`Redis`分布式锁实现 “秒杀” 业务 |   |
| `springboot-mockmvc`      | `springboot MockMVC`进行模块集成测试 |   |
| `springboot-mongodb`      | `springboot` 和`Mongodb` 整合 |   |
| `springboot-mq-kafka`      | `springboo` t集成 `Kafka` |   |
| `springboot-mq-rocket`      | `springboot RocketMQ` 使用 |   |
| `springboot-mysql-wr`      | 基于`AOP`实现读写分离 |   |
| `springboot-ons`      | 自定义阿里 `ONS starter` |   |
| `springboot-oss`      | 阿里 七牛云 腾讯 对象存储 策略模式实现 | ★★★ |
| `springboot-porduce`      | `springboot-rabbitmq`  处理消息丢失 | ★★★ |
| `springboot-rabbitmq`      | `springboot+rabbitmq` 整合示例程 |   |
| `springboot-rabbitmq-consumer`      | `springboot` 与 `RabbitMQ` 整合 |   |
| `springboot-rabbitmq-producer`      | `springboot` 与 `RabbitMQ` 整合 |   |
| `springboot-redis`                    | `redis`实战 、缓存                                           |   |
| `springboot-redis-clus`      | `redis` 结合 `springboot` 整合学习 |   |
| `springboot-redis-msg`      | `springboost redis`  消息队列 |   |
| `springboot-redisson`      | `springboot`使用 `Redisson` 实现分布式锁 |   |
| `springboot-rest-api`      | `Springboot `优雅处理返回和异常处理 | ★★★ |
| `springboot-resttemplate`      | `springboot 2.0`整合`RestTemplate` |   |
| `springboot-resttemplate-ssl`      | `springboot Mail` 发送邮件和 `Https` 配置 |   |
| `springboot-retry`      | `Retry` 重试机制 |   |
| `springboot-rpc`      | 手写RPC框架，并自定义stater 实现自动扫描注入 |   |
| `springboot-runner`      | `springboot`启动时初始化数据 |   |
| `springboot-schedule`      | 定时任务实现方式 |   |
| `springboot-schedule-two`      | `springboot-quartz`定时任务 |   |
| `springboot-sentinel`      | sentinel 学习 |   |
| `springboot-transaction`      | springboot 声明式事务 |   |
| `springboot-two-cache`      | ConcurrentHashMap+redis 实现一二级缓存，并通过redis实现分布式环境中，缓存的同步刷新 |   |
| `springboot-two-cache-again`      | 自定义注解+ehcache+redis 实现二级缓存 | ★★★  |
| `springboot-utils`      | 工具类 |   |
| `springboot-validator`      | 使用`Validator` 优雅处理参数校验、异常等 |   |
| `springboot-zookeeper`      | zookeeper 使用 |   |
| `springboot-request-response-wrapper`      | warpper、advice的使用: 参数校验、加解密、日志... |   |
| `springboot-log-wxchart-dingtalk`      | 线上日志告警-推送钉钉\企业微信 |   |



### 三、更新记录

```markdown
2020-05-29 ： 策略模式|事务、API设计、接口幂等|localDate|Warpper Advice|Spring cache
2021-07-21 ： 线上日志告警-推送钉钉\企业微信

```



