package com.zhliang.dynamic.thread.pool;


//import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
//import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
//import com.eip.cloud.dynamic.thread.pool.annotation.EnableDynamicThreadPool;
import com.eip.cloud.dynamic.thread.pool.annotation.EnableDynamicThreadPool;
import com.eip.cloud.dynamic.thread.pool.config.ThreadPoolConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDynamicThreadPool
@EnableApolloConfig
@SpringBootApplication
//@NacosPropertySource(dataId = ThreadPoolConstant.HREAD_POOL, groupId = ThreadPoolConstant.BIZ_GROUP, autoRefreshed = true)
public class ThreadPoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreadPoolApplication.class, args);
    }

}
