package com.zhliang.springboot.schedule.two.quartzother;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.schedule.two.quartzother
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/19 15:34
 * @version：V1.0
 */
//@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail printTimeJobDetail(){
        return JobBuilder.newJob(PrintTimeJob.class)//PrintTimeJob我们的业务类
                .withIdentity("PrintTimeJob")//可以给该JobDetail起一个id
                //每个JobDetail内都有一个Map，包含了关联到这个Job的数据，在Job类中可以通过context获取
                .usingJobData("msg", "Hello Quartz")//关联键值对
                .storeDurably()//即使没有Trigger关联时，也不需要删除该JobDetail
                .build();
    }
    @Bean
    public Trigger printTimeJobTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/2 * * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(printTimeJobDetail())//关联上述的JobDetail
                .withIdentity("quartzTaskService")//给Trigger起个名字
                .withSchedule(cronScheduleBuilder)
                .build();
    }

}
