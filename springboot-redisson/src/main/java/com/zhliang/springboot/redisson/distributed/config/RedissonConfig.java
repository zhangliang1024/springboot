package com.zhliang.springboot.redisson.distributed.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: colin
 * @Date: 2019/9/27 14:47
 * @Description:
 * @Version: V1.0
 */
@Configuration
public class RedissonConfig {

    @Bean
    public Redisson redisson(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        return (Redisson)Redisson.create(config);
    }
}
