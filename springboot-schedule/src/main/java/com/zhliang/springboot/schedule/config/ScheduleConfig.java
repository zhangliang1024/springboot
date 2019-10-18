package com.zhliang.springboot.schedule.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * @Author: colin
 * @Date: 2019/7/20 17:26
 * @Description: 并行定时任务配置
 * @Version: V1.0
 */
@Slf4j
//@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {
        registrar.setScheduler(taskExecutor());
    }

    /**
     * 自定义线程池
     * @return
     */
    @Bean
    public Executor taskExecutor(){
        return Executors.newScheduledThreadPool(100);
    }
}
