package com.zhliang.springboot.schedule.two.springtaskthread;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.schedule.two.springtaskthread
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/19 14:48
 * @version：V1.0
 *
 * 异步并行任务线程配置：
 */
//@Configuration
//@EnableAsync(mode = AdviceMode.PROXY,proxyTargetClass = false,order = Ordered.HIGHEST_PRECEDENCE)
public class AsyncConfigOther implements AsyncConfigurer, SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {
        registrar.setScheduler(taskScheduler());
    }

    @Override
    public Executor getAsyncExecutor() {
        Executor executor = this.taskScheduler();
        return executor;
    }

    @Bean
    public ThreadPoolTaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(200);
        scheduler.setThreadNamePrefix("myTask-");
        scheduler.setAwaitTerminationSeconds(60);
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        return scheduler;
    }

}
