# `Springboot Async` 异步任务

> 异步任务或者项目开发中碰到需要异步去解决的业务，可以通过新建线程去执行。`Spring `3.1后新增`@Async`注解来简化异步的执行，`SpringBoot`项目中结合`@EnableAsync`一起配合使用。

### 一、简介

```markdown
1. 通过`@ConfigurationProperties`注解，配置异步任务执行的线程池
```




### 二、注解实现配置注入
#### `@EnableConfigurationProperties` 注解
> `@EnableConfigurationProperties`注解的作用是：使 使用 `@ConfigurationProperties `注解的类生效
>
> 说明：
>
> 如果一个配置类只配置`@ConfigurationProperties`注解，而没有使用`@Component`，那么在`IOC容器`中是获取不到`properties` 配置文件转化的`bean`.。
>
> 说白了 `@EnableConfigurationProperties `相当于把使用 `@ConfigurationProperties` 的类进行了一次注入.
>
> 简单说：`@EnableConfigurationProperties = @ConfigurationProperties + @Component`


#### `@ConfigurationProperties`加载外部配置
##### `@ConditionalOnProperty`使用说明

> [@ConditionalOnProperty来控制Configuration是否生效](https://www.jianshu.com/p/68a75c093023)

```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
@Conditional(OnPropertyCondition.class)
public @interface ConditionalOnProperty {

    String[] value() default {}; //数组，获取对应property名称的值，与name不可同时使用  
  
    String prefix() default "";//property名称的前缀，可有可无  
  
    String[] name() default {};//数组，property完整名称或部分名称（可与prefix组合使用，组成完整的property名称），与value不可同时使用  
  
    String havingValue() default "";//可与name组合使用，比较获取到的属性值与havingValue给定的值是否相同，相同才加载配置  
  
    boolean matchIfMissing() default false;//缺少该property时是否可以加载。如果为true，没有该property也会正常加载；反之报错  
  
    boolean relaxedNames() default true;//是否可以松散匹配，至今不知道怎么使用的  
} 
AutowiredAnnotationBeanPostProcessor
```
1. `Java `配置类
```java
//ignoreInvalidFields : 属性配置错误的值时，而又不希望 Spring Boot 应用启动失败.设置 ignoreInvalidFields 属性为 true (默认为 false)
//ignoreUnknownFields : 当配置文件中有一个属性实际上没有绑定到 @ConfigurationProperties 类时，我们可能希望启动失败.将 ignoreUnknownFields 属性设置为 false (默认是 true)
@Configuration   //配置类注解，被自动扫描发现
@PropertySource("classpath:application.yml") //指明配置源文件位置
@ConfigurationProperties(prefix="environment",ignoreInvalidFields=false) //指明前缀
public class EnvConfig {
    private final Dev dev=new Dev();
    private final Production production=new Production();
```
2. 我们向项目中添加依赖: 使用 `Spring Boot Configuration Processor` 完成自动补全
`pom.xml`
```xml
 <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
</dependency>
```

### 三、`Async` 实现
1. `AsyncProperties` 线程池配置
```java
@ConfigurationProperties(prefix = AsyncProperties.AUDIT_LOG_ASYNC)
public class AsyncProperties {

    public static final String AUDIT_LOG_ASYNC = "audit.log.async";

    public static final String AUDIT_LOG_THREAD_NAME_PREFIX = "audit-log-async-";

    /**
     *  默认情况下，在创建了线程池后，线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务，
     *	当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到缓存队列当中；
     *  当队列满了，就继续创建线程，当线程数量大于等于maxPoolSize后，开始使用拒绝策略拒绝
     */
    private boolean enable = true;
    /**
     * 核心线程数（默认线程数）
     */
    private int corePoolSize = 3;
    /**
     * 最大线程数
     */
    private int maxPoolSize = 6;
    /**
     * 允许线程空闲时间（单位：默认为秒）
     */
    private int keepAliveTime = 40;
    /**
     * 缓冲队列大小
     */
    private int queueCapacity = 200;
    /**
     * 等待时长
     */
    private int awaitTerminationSeconds = 60;
```
2. `AsyncConfig` 异步任务配置
```java
@EnableAsync
@Configuration
@ConditionalOnProperty(value = "audit.log.async.enable",havingValue = "true")
@EnableConfigurationProperties(AsyncProperties.class)
public class AsyncConfig {

    // bean的名称，默认为首字母小写的方法名
    @Bean("auditExecutor")
    public ThreadPoolTaskExecutor taskExecutor(AsyncProperties async) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(async.getCorePoolSize());
        executor.setMaxPoolSize(async.getMaxPoolSize());
        executor.setQueueCapacity(async.getQueueCapacity());
        executor.setKeepAliveSeconds(async.getKeepAliveTime());
        executor.setThreadNamePrefix(AsyncProperties.AUDIT_LOG_THREAD_NAME_PREFIX);
        // 线程池对拒绝任务的处理策略
        //AbortPolicy:直接抛出java.util.concurrent.RejectedExecutionException异常
        //CallerRunsPolicy:主线程直接执行该任务，执行完之后尝试添加下一个任务到线程池中，可以有效降低向线程池内添加任务的速度
        //DiscardOldestPolicy:抛弃旧的任务、暂不支持；会导致被丢弃的任务无法再次被执行
        //DiscardPolicy:抛弃当前任务、暂不支持；会导致被丢弃的任务无法再次被执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //等待时长
        executor.setAwaitTerminationSeconds(async.getAwaitTerminationSeconds());
        // 初始化
        executor.initialize();
        return executor;
    }
}
```

3. `LogService` 业务实现类
```java
@Async("auditExecutor")
@Component
public class LogService {
}
```
4. `application.yml `配置文件
```yaml
audit:
  log:
    async:
      enable: true
      core-pool-size: 10
      max-pool-size: 15
      keep-alive-time: 5
      queue-capacity: 200
      await-termination-seconds: 100
```
5. `Async`使用注意事项
```markdown
1. 主程序增加 @EnableAsync注解标识，启动异步执行
2. 执行异步任务的类或方法增加 @Async注解，标识为具体的执行业务
3. 最好配合自定义线程池，异步任务使用单独的线程池去执行

@Async失效的情况
1.异步方法使用static修饰
2.异步类没有使用@Component注解（或其他注解）导致spring无法扫描到异步类
3.异步方法不能与被调用的异步方法在同一个类中
4.类中需要使用@Autowired或@Resource等注解自动注入，不能自己手动new对象
5.如果使用SpringBoot框架必须在启动类中增加@EnableAsync注解
```

6. `@Async`调用中的异步处理机制
```markdown
异步处理的方式：
1.对于无返回值的异步任务，配置AsyncExceptionConfig类，统一处理
2.对于有返回值的异步任务，可以在contoller层捕获异常，进行处理
  或者
  通过AsyncResult捕获异常
```
7. `@Async`调用中的事务处理机制
```markdown
在@Async标注的方法，同时也使用@Transactional进行标注；在其调用数据库操作之时，将无法产生事务管理的控制，原因就在于其是基于异步处理的操作。

可以将需要事务管理操作的方法放置到异步方法内部，在内部被调用的方法上添加@Transactional
示例：
方法A， 使用了@Async/@Transactional来标注，但是无法产生事务控制的目的。
方法B， 使用了@Async来标注，B中调用了C、D，C/D分别使用@Transactional做了标注，则可实现事务控制的目的。
```

### 四、参考文档
```markdown
@EnableConfigurationProperties注解 说明
[EnableConfigurationProperties 注解](https://www.jianshu.com/p/7f54da1cb2eb)
[EnableConfigurationProperties是如何起作用的](https://segmentfault.com/a/1190000018987185)

@ConfigurationProperties加载外部配置
[ConfigurationProperties加载外部配置](https://blog.csdn.net/qq_33745102/article/details/85720888)
★★★
[ConfigurationProperties注解使用姿势,这一篇就够了](https://segmentfault.com/a/1190000020183307)

SpringBoot线程池的创建、@Async配置步骤及注意事项
[SpringBoot线程池的创建@Async配置步骤及注意事项](https://blog.csdn.net/Muscleheng/article/details/81409672)
★异步任务接受返回值：通过循环确认Fature回调方式判断
[springboot+async异步接口实现和调用](https://www.cnblogs.com/shamo89/p/9095380.html)
★Spring用TaskExecutor和TaskScheduler接口提供了异步执行和调度任务的抽象
[Spring Boot中@Async的作用](https://www.cnblogs.com/xuzhujack/p/11322439.html)
[Spring中@Async用法总结](https://www.cnblogs.com/lcngu/p/6185363.html)

SpringBoot @Async异步异常处理
[SpringBoot@Async异步异常处理](https://www.jianshu.com/p/11c78717799b)

```