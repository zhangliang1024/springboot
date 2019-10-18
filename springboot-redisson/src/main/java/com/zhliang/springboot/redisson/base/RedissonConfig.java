package com.zhliang.springboot.redisson.base;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.ConfigSupport;
import org.redisson.config.SingleServerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: colin
 * @Date: 2019/9/10 10:06
 * @Description:
 * 本Demo演示参考：
 *  [SpringBoot项目开发(十五)：redisson实现分布式锁]
 *     (https://blog.csdn.net/zhuyu19911016520/article/details/80418161)
 * @Version: V1.0
 */
//@Configuration
public class RedissonConfig {

    /**
     * 通过配置文件注入: https://blog.csdn.net/lms1719/article/details/83652578
     * @return
     * @throws IOException
     */
    //@Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() throws IOException {
        RedissonClient client =
                Redisson.create(fromYAML(new ClassPathResource("redisson-single.yml").getInputStream()));
        return client;
    }

    /**
     * 自己重新源码还是报错：
     * com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException: Unrecognized field "  idleConnectionTimeout" (class org.redisson.config.Config), not marked as ignorable (18 known properties: "useLinuxNativeEpoll", "eventLoopGroup", "addressResolverGroupFactory", "keepPubSubOrder", "nettyThreads", "threads", "transportMode", "singleServerConfig", "sentinelServersConfig", "executor", "codec", "replicatedServersConfig", "clusterServersConfig", "referenceCodecProvider", "masterSlaveServersConfig", "elasticacheServersConfig", "lockWatchdogTimeout", "referenceEnabled"])
     */
    public static Config fromYAML(InputStream inputStream) throws IOException {
        Config config = new Config();
        ConfigSupport support = new ConfigSupport();
        SingleServerConfig singleServerConfig = support.fromYAML(inputStream, SingleServerConfig.class);
        config.useSingleServer();
        return config;
    }

    //@Bean
    public RedissonClient redisson1() throws IOException {
        // 本例子使用的是yaml格式的配置文件，读取使用Config.fromYAML，如果是Json文件，则使用Config.fromJSON
        Config config = Config.fromYAML(RedissonConfig.class.getClassLoader().getResource("redisson-config.yml"));
        return Redisson.create(config);
    }


    /**
     * 直接注入
     * 报错： o.redisson.client.handler.CommandsQueue : Exception occured. Channel: [id: 0xb5ffdd00, L:/127.0.0.1:58815
     * - R:127.0.0.1/127.0.0.1:26380]
     *
     * java.io.IOException: 远程主机强迫关闭了一个现有的连接。
     *   是版本号不一致导致
     *   <dependency>
*             <groupId>org.redisson</groupId>
*             <artifactId>redisson</artifactId>
*             <version>3.6.5</version>
*         </dependency>
*         <dependency>
*             <groupId>org.springframework.boot</groupId>
*             <artifactId>spring-boot-starter-data-redis</artifactId>
*         </dependency>
     *
     *    [3.6.5不报错；3.8.2报错以上错误]
     */
    @Bean
    public RedissonClient MyRedisson() {
        //支持单机，主从，哨兵，集群等模式
        //此为单机模式
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        //哨兵模式
        //config.useSentinelServers()
        //        .setMasterName("mymaster")
        //        .addSentinelAddress("redis://192.168.1.1:26379");
        return (Redisson)Redisson.create(config);
    }
}
