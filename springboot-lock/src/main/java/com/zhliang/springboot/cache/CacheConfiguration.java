package com.zhliang.springboot.cache;

import cn.gjing.cache.CaffeineCache;
import cn.gjing.cache.RedisCache;
import cn.gjing.cache.SecondCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: colin
 * @Date: 2019/8/23 15:46
 * @Description:
 * @Version: V1.0
 */
@Configuration
public class CacheConfiguration {


    @Bean
    public SecondCache secondCache(){
        return SecondCache.builder()
                .cachePrefix("锁的前缀")
                .dynamic(true)
                .build();
    }

    @Bean
    public RedisCache redisCache() {
        return RedisCache.builder()
                .expire(10)
                .build();
    }

    @Bean
    public CaffeineCache caffeineCache() {
        return CaffeineCache.builder()
                .expireAfterWrite(3000)
                .build();
    }

}
