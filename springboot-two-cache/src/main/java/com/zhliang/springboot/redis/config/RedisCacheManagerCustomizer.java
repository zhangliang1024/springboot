package com.zhliang.springboot.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.redis.config
 * @类描述：配置缓存
 * @创建人：colin
 * @创建时间：2019/12/4 09:31
 * @version：V1.0
 */
//@Configuration
public class RedisCacheManagerCustomizer {

    @Primary
    @Bean
    public RedisCacheManager getRedisCacheManager(RedisConnectionFactory connectionFactory){
        RedisCacheWriter cacheWriter = RedisCacheWriter.lockingRedisCacheWriter(connectionFactory);
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer(loader);
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(jdkSerializer);

        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
        cacheConfig = cacheConfig.entryTtl(Duration.ofSeconds(3600));//设置所有的超时时间

        //设置单个缓存的超时时间

        Map<String, RedisCacheConfiguration> initialCacheConfigurations = new HashMap<>();
        initialCacheConfigurations.put("user",cacheConfig.entryTtl(Duration.ofSeconds(60)));


        RedisCacheManager cacheManager = new RedisCacheManager(cacheWriter, cacheConfig,initialCacheConfigurations);

        return cacheManager;
    }
}
