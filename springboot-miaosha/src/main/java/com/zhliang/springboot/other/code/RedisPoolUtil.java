package com.zhliang.springboot.other.code;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * @Author: colin
 * @Date: 2019/9/9 11:51
 * @Description: 对Jedis的API进行封装，实现一些分布式锁需要的操作
 * @Version: V1.0
 */
@Slf4j
public class RedisPoolUtil {

    private static RedisPool redisPool;

    /**
     * 根据key获取value
     * @param key
     * @return
     */
    public static String get(String key){
        Jedis jedis = null;
        String result = null;
        try{
            jedis = redisPool.getJedis();
            result = jedis.get(key);
        }catch (Exception e){
            log.error("获取key{}对应value异常",key);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return result;
    }

    /**
     * 设置key 如果key不存在的时候
     * @param key
     * @param value
     * @return
     */
    public static Long setnx(String key,String value){
        Jedis jedis = null;
        Long result = null;
        try{
            jedis = redisPool.getJedis();
            result = jedis.setnx(key,value);
        }catch (Exception e){
            log.error("redis setnx 异常 {}",key);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return result;
    }

    /**
     * 给key设置过期时间
     * @param key
     * @param seconds
     * @return
     */
    public static Long expire(String key,int seconds){
        Jedis jedis = null;
        Long result = null;
        try{
            jedis = redisPool.getJedis();
            result = jedis.expire(key,seconds);
        }catch (Exception e){
            log.error("redis expire 异常 {}",key);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return result;
    }

    /**
     * 原子方法：给key设置新值 返回旧值
     * @param key
     * @param value
     * @return
     */
    public static String getSet(String key,String value){
        Jedis jedis = null;
        String result = null;
        try{
            jedis = redisPool.getJedis();
            result = jedis.getSet(key,value);
        }catch (Exception e){
            log.error("redis getSet 异常",key);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return result;
    }

    /**
     * 删除key
     * @param key
     * @return
     */
    public static Long del(String key){
        Jedis jedis = null;
        Long result = null;
        try{
            jedis = redisPool.getJedis();
            result = jedis.del(key);
        }catch (Exception e){
            log.error("redis del 异常 {}",key);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return result;
    }
}
