package com.zhliang.springboot.lock.redis.util;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author: colin
 * @Date: 2019/8/27 15:45
 * @Description:
 * @Version: V1.0
 */
@Component
public class RedisUtils {

    private static JedisPool jedisPool;


    public boolean setnx(String key, String val) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis == null) {
                return false;
            }
            return jedis.set(key, val, "NX", "PX", 1000 * 60).equalsIgnoreCase("ok");
        } catch (Exception ex) {
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }
}
