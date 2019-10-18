package com.zhliang.springboot.redisson.aspect;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @Author: colin
 * @Date: 2019/9/10 11:06
 * @Description:  基于Redis的配置类 配置Redisson的客户端bean
 * @Version: V1.0
 */
//@Configuration
public class RedisConfig {


    @Bean(name = {"template","stringRedisTemplate"})
    public StringRedisTemplate string(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(factory);
        return template;
    }

    @Bean
    public Redisson redisson() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        return (Redisson) Redisson.create(config);
    }
}
