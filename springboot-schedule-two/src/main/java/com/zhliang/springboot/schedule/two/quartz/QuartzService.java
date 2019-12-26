package com.zhliang.springboot.schedule.two.quartz;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.schedule.two.quartz
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/19 15:02
 * @version：V1.0
 */
@Slf4j
public class QuartzService extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext job) throws JobExecutionException {
        log.info("quartz job : {}",new Date());
    }
}
