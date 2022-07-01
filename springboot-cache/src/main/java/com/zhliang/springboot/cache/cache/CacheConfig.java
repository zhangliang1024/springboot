package com.zhliang.springboot.cache.cache;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.concurrent.TimeUnit;

/**
 * @创建人：zhiang
 * @创建时间：2020/5/11 10:46
 * @version：V1.0
 */
@Configuration
public class CacheConfig {

    @Bean
    public Cache<String, String> getCache() {
        // 缓存有效期为Constant.EXPIRE_TIME 秒
        return CacheBuilder.newBuilder()
                .maximumSize(Constant.MAX_SIZE)
                .expireAfterWrite(Constant.EXPIRE_TIME, TimeUnit.SECONDS).build();
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public LocalCacheService localCacheService(){
        return new LocalCacheService();
    }

    @Bean
    @ConditionalOnClass(RedisTemplate.class)
    public RedisCacheService redisCacheService(){
        return new RedisCacheService();
    }


}