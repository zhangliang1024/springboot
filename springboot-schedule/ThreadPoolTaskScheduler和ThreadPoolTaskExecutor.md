# ThreadPoolTaskScheduler和ThreadPoolTaskExecutor

## 一、代码示例
```java
@EnableAsync
@Configuration
public class ThreadPoolConfig {

    @Bean(name = AsyncExecutionAspectSupport.DEFAULT_TASK_EXECUTOR_BEAN_NAME)
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程池大小
        executor.setCorePoolSize(10);
        //最大线程数
        executor.setMaxPoolSize(30);
        //队列容量
        executor.setQueueCapacity(100);
        //活跃时间
        executor.setKeepAliveSeconds(60);
        //线程名字前缀
        executor.setThreadNamePrefix("taskExecutor-");
        // 设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 线程池对拒绝任务的处理策略,当线程池没有处理能力的时候，该策略会直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
    
    @Bean("taskExecutor")
    public Executor taskExecutor() {
        // 创建一个线程池对象
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        // 定义一个线程池大小
        scheduler.setPoolSize(100);
        // 线程池名的前缀
        scheduler.setThreadNamePrefix("taskExecutor-");
        // 设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        // 设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
        scheduler.setAwaitTerminationSeconds(60);
        // 线程池对拒绝任务的处理策略,当线程池没有处理能力的时候，该策略会直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务
        scheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return scheduler;
    }

}
```

## 二、区别分析
> 从继承结构分析来看，两者只相差一个`TaskScheduler`，而`TaskScheduler`是专门用于调度任务的类，这也从根本上区分了两者的用途
- `ThreadPoolTaskExecutor`是一个专门用于执行任务的类
- `ThreadPoolTaskScheduler`是一个专门用于调度任务的类
> 故，在两者之间选择归结为：是否需要执行任务的调度

## 三、文档
* [ThreadPoolTaskScheduler和ThreadPoolTaskExecutor](https://blog.csdn.net/weixin_42526326/article/details/121810253)
