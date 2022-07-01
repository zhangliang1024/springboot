package com.zhliang.springboot.cache.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @创建人：zhiang
 * @创建时间：2020/5/11 11:31
 * @version：V1.0
 */
public class RedisCacheService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 判断key是否存在
     */
    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }
    /**
     * 普通缓存获取
     */
    public String get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     */
    public void set(String key,String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 普通缓存放入并设置时间
     */
    public void set(String key,String value,long time){
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    /**
     * 删除缓存
     */
    public void del(String key){
        redisTemplate.delete(key);
    }

}
