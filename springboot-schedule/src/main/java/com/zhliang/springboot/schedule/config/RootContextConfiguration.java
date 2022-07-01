package com.zhliang.springboot.schedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;

/**
 * @Author: colin
 * @Date: 2019/7/20 17:40
 * @Description: 异步并行定时任务配置
 * @Version: V1.0
 */
//@Configuration
//@EnableAsync(mode = AdviceMode.PROXY,proxyTargetClass = false,order = Ordered.HIGHEST_PRECEDENCE)
public class RootContextConfiguration implements AsyncConfigurer, SchedulingConfigurer {

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
