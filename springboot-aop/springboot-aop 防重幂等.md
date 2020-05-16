# Spring Boot 使用 AOP 防止重复提交
>[Spring Boot 使用 AOP 防止重复提交](https://www.jianshu.com/p/09860b74658e)
> - 以上实现的思路个人感觉更像是 接口幂等的处理
> - 防止表单重复提交，如果直接锁住接口，颗粒度太粗
```markdown
2020-05-06 重新理解
业务规则说明：
服务器端实现方案:同一客户端在2秒内对同一URL的提交视为重复提交

1. 这种方式通过：token\jessionId + url 来做唯一标识，颗粒度叫粗。当前用户在一定时间内无法访问
2. 但如果并发请求量不大，做为管理类系统或用户量较少的系统。方案还算合理，不需要前端配置就可以实现一定的“幂等”控制
3. 使用redis方便扩展

4. 原始方案使用本地"cache"来代替 "redis"

```

> [Spring Boot+Redis+Interceptor+自定义Annotation实现接口自动幂等](https://www.jianshu.com/p/c806003a8530)
>
> 个人理解对 幂等的理解不是很清楚：实现很大程度上是基于 防重复提交的思路来



### 三、参考文档

[Spring Boot 使用 AOP 防止重复提交](https://www.jianshu.com/p/09860b74658e)

[参考Demo](https://github.com/TavenYin/taven-springboot-learning/tree/master/repeat-submit-intercept)

[spring boot 防止重复提交 Guava本地缓存方案](https://www.jianshu.com/p/09c6b05b670a)

