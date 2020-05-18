package com.zhliang.springboota.sync.async;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @创建人：zhiang
 * @创建时间：2020/5/8 17:03
 * @version：V1.0
 */
@Data
@ConfigurationProperties(prefix = AsyncProperties.AUDIT_LOG_ASYNC)
public class AsyncProperties {

    public static final String AUDIT_LOG_ASYNC = "audit.log.async";

    public static final String AUDIT_LOG_THREAD_NAME_PREFIX = "audit-log-async-";

    /**
     *  默认情况下，在创建了线程池后，线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务，
     *	当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到缓存队列当中；
     *  当队列满了，就继续创建线程，当线程数量大于等于maxPoolSize后，开始使用拒绝策略拒绝
     */
    private boolean enable = false;
    /**
     * 核心线程数（默认线程数）
     */
    private int corePoolSize = 3;
    /**
     * 最大线程数
     */
    private int maxPoolSize = 6;
    /**
     * 允许线程空闲时间（单位：默认为秒）
     */
    private int keepAliveTime = 4;
    /**
     * 缓冲队列大小
     */
    private int queueCapacity = 200;
    /**
     * 等待时长
     */
    private int awaitTerminationSeconds = 60;

}
