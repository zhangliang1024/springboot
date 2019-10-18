package com.zhliang.springboot.schedule;

import com.zhliang.springboot.schedule.config.RootContextConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @SpringBootApplication:
 * @Configuration: 告诉Spring这是一个配置类，里面的所有标注了@Bean的方法的返回值将被注册为一个Bean
 * @EnableAutoConfiguration: 告诉Spring基于class path的设置、其他bean以及其他设置来为应用添加各种Bean
 * @ComponentScan: 告诉Spring扫描Class path下所有类来生成相应的Bean
 */


@SpringBootApplication
@EnableScheduling //开启支持定时任务：启动场景
public class SpringbootScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootScheduleApplication.class, args);

        //AnnotationConfigApplicationContext applicationContext =
        //        new AnnotationConfigApplicationContext();
        //applicationContext.register(RootContextConfiguration.class);
        //applicationContext.refresh();


    }

    /**
     * 自定义任务线程池：如果没有则使用 默认定时任务池
     * 使用：config配置Bean的时候，如果存在close或者shutdown方法，则在Bean销毁时，会自动执行该方法。
     *      如果不想执行，则添加@Bean(destroyMethod = "shutdown")来防止触发销毁方法
     */
    @Bean(destroyMethod = "shutdown")
    public Executor taskExecutor(){
        return new ScheduledThreadPoolExecutor(10, new ThreadFactory() {
            private AtomicInteger max = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"mySchedulAnno-" + max.incrementAndGet());
            }
        });
    }


}
