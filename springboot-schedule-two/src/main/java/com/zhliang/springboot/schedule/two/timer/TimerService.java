package com.zhliang.springboot.schedule.two.timer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.schedule.two.timer
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/19 16:54
 * @version：V1.0
 */
@Slf4j
public class TimerService {

    public static void main(String[] args) {
        //doTimer();
        ScheduledExecutorService();
    }

    /**
     * Timer : 不支持多线程，任务串行，不捕获异常
     * 如果某个任务异常了，整个timer就无法运行
     */
    public static String doTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("Timer定时任务启动：" + new Date());
            }
        }, 1000, 1000);//延迟1秒启动，每1秒执行一次
        return "timer";
    }

    public static String ScheduledExecutorService() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("ScheduledExecutorService定时任务执行：" + new Date());
            }
        }, 1, 1, TimeUnit.SECONDS);//首次延迟1秒，之后每1秒执行一次

        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("ScheduledExecutorService定时任务执行测试多线程：" + new Date());
            }
        }, 1, 1, TimeUnit.SECONDS);//首次延迟1秒，之后每1秒执行一次


        log.info("ScheduledExecutorService定时任务启动：" + new Date());
        return "ScheduledExecutorService!";
    }

}
