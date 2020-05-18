package com.zhliang.springboota.sync.async;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @类描述：异步任务配置
 * @创建人：zhiang
 * @创建时间：2020/5/8 16:11
 * @version：V1.0
 */
@EnableAsync
@Configuration
@ConditionalOnProperty(value = "audit.log.async.enable",havingValue = "true")
@EnableConfigurationProperties(AsyncProperties.class)
public class AsyncConfig {

    @Bean("auditExecutor")
    public ThreadPoolTaskExecutor taskExecutor(AsyncProperties async) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(async.getCorePoolSize());
        executor.setMaxPoolSize(async.getMaxPoolSize());
        executor.setQueueCapacity(async.getQueueCapacity());
        executor.setKeepAliveSeconds(async.getKeepAliveTime());
        executor.setThreadNamePrefix(AsyncProperties.AUDIT_LOG_THREAD_NAME_PREFIX);
        // 线程池对拒绝任务的处理策略
        // CallerRunsPolicy：由调用线程（提交任务的线程）处理该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //等待时长
        executor.setAwaitTerminationSeconds(async.getAwaitTerminationSeconds());
        // 初始化
        executor.initialize();
        return executor;
    }
}
