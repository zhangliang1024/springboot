package com.zhliang.springboot.schedule.two.springtaskthread;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.schedule.two.springtaskthread
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/19 15:20
 * @version：V1.0
 *
 *
 * 定时任务线程配置：多个任务并行执行，每个任务单独在一个线程中执行
 */
//@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(3));
    }
}
