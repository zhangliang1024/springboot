# Springboot 定时任务简单使用

### Springboot 串行定时任务
```properties
1. Springboot 默认的执行方式是：串行执行，及有多个定时任务时，都是一个线程串行执行，不需要手动配置

2. 常用注解及介绍
@EnableScheduling : 开启springboot定时任务功能，并执行@Scheduled注解标注的定时任务方法
@Scheduled ：标注这个方法是一个定时任务方法，有多种配置可选
支持：corn表达式，指定任务在特定时间执行
     fixedRate, 以特定的频率执行任务\
     fixedRateString, 以String形式的配置执行任务         
```
#### 代码实现
```java
@SpringBootApplication
@EnableScheduling //开启支持定时任务：启动场景
public class SpringbootScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootScheduleApplication.class, args);
    }

}

@Slf4j
@Component
public class ShcedulerTask {

    @Scheduled(cron = "0/1 * * * * ?")
    public void scheduleTask(){
        log.info("【定时任务执行开始】");
        Thread thread = Thread.currentThread();
        System.out.println("当前线程ID：" + thread.getId());
        log.info("【定时任务执行】线程ID：{}，线程名称：{}",thread.getId(),thread.getName());
    }
}
```

### Springboot 并行定时任务
```java
/**
 * 实现接口：SchedulingConfigurer 并重写configureTasks()方法
 * 1. 使用自定义的线程池配置
 */
@Slf4j
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {
        registrar.setScheduler(taskExecutor());
    }

    @Bean
    public Executor taskExecutor(){
        return Executors.newScheduledThreadPool(100);
    }
} 
```

