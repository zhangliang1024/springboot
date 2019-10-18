package com.zhliang.springboot.zookeeper.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: colin
 * @Date: 2019/9/26 17:56
 * @Description:
 * @Version: V1.0
 */
@Configuration
@EnableConfigurationProperties({ZkProperties.class})
@ConditionalOnProperty(value = {"zookeeper.enabled"},matchIfMissing = false)
public class ZkConfigurer {

    @Autowired
    ZkProperties zkProperties;


    @Bean(initMethod = "init", destroyMethod = "stop")
    public ZkClient zkClient() {
        ZkClient zkClient = new ZkClient(zkProperties);
        return zkClient;
    }

    /**
     * 用于分布于测试，正式使用请删除
     * @return
     */
    @Bean(initMethod = "init", destroyMethod = "stop")
    public ZkClient zkClientTest() {
        ZkClient zkClient = new ZkClient(zkProperties);
        return zkClient;
    }
}
