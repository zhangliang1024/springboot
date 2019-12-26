package com.zhliang.springboot.schedule.two.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.schedule.two.quartz
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/19 15:05
 * @version：V1.0
 */
//@Configuration
public class QuartzConfig {

    /**
     * 1. 加入依赖即可 不需要@EnableScheduling注解
     * <dependency>
     *     <groupId>org.springframework.boot</groupId>
     *     <artifactId>spring-boot-starter-quartz</artifactId>
     * </dependency>
     *
     *
     */


    @Bean
    public JobDetail teatQuartzDetail() {
        return JobBuilder.newJob(QuartzService.class).withIdentity("testQuartz").storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(2)  //设置时间周期单位秒
                .repeatForever();
        return TriggerBuilder.newTrigger()
                .forJob(teatQuartzDetail())
                .withIdentity("testQuartz")
                .withSchedule(scheduleBuilder)
                .build();
    }

}
