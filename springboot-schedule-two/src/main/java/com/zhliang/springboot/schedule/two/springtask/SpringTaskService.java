package com.zhliang.springboot.schedule.two.springtask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.schedule.two.springtask
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/19 14:43
 * @version：V1.0
 */
@Slf4j
//@Component
public class SpringTaskService {

    /**
     * Spring3.0以后自带的task，可以将它看成一个轻量级的Quartz，而且使用起来比Quartz简单许多。
     * @EnableScheduling
     * @Scheduled
     *  配合使用，不需要额外的依赖
     *
     * @Scheduled注解来设置任务的执行时间
     * corn: 通过表达式来配置任务执行时间
     * fixedRate: 按一定频率执行的定时任务 可能会同时运行多个相同任务
     * fixedDelay: 按一定频率执行的定时任务，与上面不同的是，改属性可以配合initialDelay， 定义该任务延迟执行时间
     *
     *  策略：三个定时任务串行执行都在一个线程中。
     *       如果其中一个任务卡死，会导致后续任务无法继续执行
     *
     *  @Async
     *  使用线程池，异步构建。每一个任务都是在不同的线程中
     */


    //@Async(value = "taskExecutor")
    @Scheduled(cron = "${scheduleTask.cron1}")
    public void scheduled() {
        log.info("=====>>>>>使用cron  {}", System.currentTimeMillis());
    }

    //@Async("scheduledPoolTaskExecutor")
    @Scheduled(fixedRate = 5000)
    public void scheduled1() {
        log.info("=====>>>>>使用fixedRate{}", System.currentTimeMillis());
    }

    //@Async
    @Scheduled(fixedDelay = 5000)
    public void scheduled2() {
        log.info("=====>>>>>fixedDelay{}", System.currentTimeMillis());
    }
}
