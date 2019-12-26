package com.zhliang.springboot.schedule.two.quartzother;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.schedule.two.quartzother
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/19 15:33
 * @version：V1.0
 */

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务业务处理类，我们继承QuartzJobBean
 * 重写executeInternal方法来实现具体的定时业务逻辑
 * Spring boot定时任务及集成Quartz:
 *  https://blog.csdn.net/qq_27046951/article/details/82805259
 */
public class PrintTimeJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        //获取JobDetail中关联的数据
        String msg = (String) context.getJobDetail().getJobDataMap().get("msg");
        System.out.println("current time :"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "---" + msg);
    }
}
