package com.zhliang.springboot.schedule.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: colin
 * @Date: 2019/7/20 17:13
 * @Description: 简单定时任务： 如果不配置，则默认串行执行
 * @Version: V1.0
 */
@Slf4j
//@Component
public class ShcedulerTaskOne {

    /**
     * @Scheduled 注解用于标注这个方法是一个定时任务的方法，有多种配置可选。
     *            cron支持cron表达式，指定任务在特定时间执行；
     *            fixedRate以特定频率执行任务；
     *            fixedRateString以string的形式配置执行频率。
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void scheduleTaskTwo(){
        log.info("【定时任务执行开始 Two】");
        Thread thread = Thread.currentThread();
        System.out.println("Two 当前线程ID：" + thread.getId());
        log.info("【定时任务执行 Two】线程ID：{}，线程名称：{}",thread.getId(),thread.getName());
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void scheduleTaskOne(){
        log.info("【定时任务执行开始 One】");
        Thread thread = Thread.currentThread();
        System.out.println("One 当前线程ID：" + thread.getId());
        log.info("【定时任务执行 One】线程ID：{}，线程名称：{}",thread.getId(),thread.getName());
    }
}
