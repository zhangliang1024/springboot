package com.zhliang.springboot.schedule.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: colin
 * @Date: 2019/7/25 20:50
 * @Description: https://www.tianmaying.com/tutorial/spring-scheduling-task
 * @Version: V1.0
 */
//@Component
public class ShcedulerTaskTwo {

    public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Integer count_0 = 1;
    private Integer count_1 = 1;
    private Integer count_2 = 1;

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() throws InterruptedException {
        System.out.println(String.format("---第%s次执行，当前时间为：%s", count_0++, format.format(new Date())));
    }

    @Scheduled(fixedDelay = 5000)
    public void reportCurrentTimeAfterSleep() throws InterruptedException {
        System.out.println(String.format("===第%s次执行，当前时间为：%s", count_1++, format.format(new Date())));
    }

    @Scheduled(cron = "0 0 1 * * *")
    public void reportCurrentTimeCron() throws InterruptedException {
        System.out.println(String.format("+++第%s次执行，当前时间为：%s", count_2++, format.format(new Date())));
    }
}
