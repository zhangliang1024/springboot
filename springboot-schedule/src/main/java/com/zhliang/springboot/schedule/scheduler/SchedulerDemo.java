package com.zhliang.springboot.schedule.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: colin
 * @Date: 2019/7/26 10:15
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Component
public class SchedulerDemo {


    /**
     * 每次方法执行完毕后，等待5s在执行此方法。 同时只能有一个线程运行此方法
     */
    @Scheduled(fixedDelay = 5000)
    public void fixedDelay()throws Exception{
        Thread.sleep(6000);
        log.info("【方法执行完毕，5s后会再次执行此方法】");
    }


    /**
     * 每隔 5s执行一次此方法，无论之前的方法是否执行完毕。同时可能有N个线程执行此方法
     */
    @Scheduled(fixedRate = 5000)
    public void fixedRate() throws Exception{
        Thread.sleep(6000);
        log.info("【每隔 5s都会执行此方法，无论是否执行完毕】");
    }


    /**
     * initialDelay: 每次调用此方法前的等待时间
     */
    @Scheduled(initialDelay = 1000,fixedRate = 5000)
    public void initialDelayAndfixedRate() throws Exception{
        Thread.sleep(5000);
        log.info("【每次调用此方法前等待 1s】");
    }


    /**
     * 支持 cron语法
     * 参数的意思： second|minute|hour|day of month|month|day of week
     * 示例：每周一至周五 每隔5s执行一次
     */
    @Scheduled(cron = "*/5 * * * * SUN-MON")
    public void cron() throws Exception{
        Thread.sleep(5000);
        log.info("【使用cron表达式执行任务 周一至周五每隔5s执行一次】");
    }


}
