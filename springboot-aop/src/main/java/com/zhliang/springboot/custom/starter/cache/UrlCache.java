package com.zhliang.springboot.custom.starter.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.custom.starter.cache
 * @类描述： 本地缓存代替 redis 方案
 * @创建人：zhiang
 * @创建时间：2020/5/6 14:44
 * @version：V1.0
 */
@Configuration
public class UrlCache {

    @Bean
    public Cache<String, Integer> getCache() {
        // 缓存有效期为2秒
        return CacheBuilder.newBuilder().expireAfterWrite(2L, TimeUnit.SECONDS).build();
    }
}
