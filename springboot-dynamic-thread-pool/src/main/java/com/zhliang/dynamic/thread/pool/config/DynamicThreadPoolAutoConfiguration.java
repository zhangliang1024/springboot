package com.zhliang.dynamic.thread.pool.config;

import com.zhliang.dynamic.thread.pool.alarm.DynamicThreadPoolAlarm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @类描述：启用线程池监控
 * @创建人：zhiang
 * @创建时间：2021/11/9 09:24
 */
@Configuration
public class DynamicThreadPoolAutoConfiguration {

    @Bean
    public DynamicThreadPoolAlarm dynamicThreadPoolAlarm() {
        return new DynamicThreadPoolAlarm();
    }

}
