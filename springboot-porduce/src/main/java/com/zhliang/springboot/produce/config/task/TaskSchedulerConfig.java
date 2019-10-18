package com.zhliang.springboot.produce.config.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @Author: colin
 * @Date: 2019/9/23 15:37
 * @Description:
 * @Version: V1.0
 */
@Configuration
@EnableScheduling
public class TaskSchedulerConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskScheduler());
    }

    @Bean
    public Executor taskScheduler(){
        return Executors.newScheduledThreadPool(100);
    }

}
