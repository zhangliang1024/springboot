package com.zhliang.springboot.other.high;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;
import java.util.Objects;

/**
 * @Author: colin
 * @Date: 2019/9/9 15:06
 * @Description: 封装Redis 工具类
 * @Version: V1.0
 */
@Slf4j
public class JedisUtil {


    private static final Long UNLOCK_SUCCESS = 1L;
    private static JedisPool jedisPool;

    static {
        GenericObjectPoolConfig<Object> config = new GenericObjectPoolConfig<>();
        jedisPool = new JedisPool(config, "127.0.0.1");
    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    public static void release(Jedis jedis){
        if(jedis != null){
            jedis.close();
        }
    }


    /**
     * Redis 2.6.12版本后，针对分布式锁进行了加强
     * @param key
     * @param value
     * @param expireMillis
     * @return
     */
    public static boolean set(String key,String value,Long expireMillis){
        Jedis jedis = null;
        boolean falg = false;
        try{
            jedis = getJedis();
            //nx = not exist,px = 单位毫秒
            String result = jedis.set(key, value, "NX", "PX", expireMillis);
            if(result != null && result.equalsIgnoreCase("OK")){
                falg = true;
            }
        }catch (Exception e){
            log.error("异常 {}",e.getMessage());
        }finally {
            release(jedis);
        }
        return falg;
    }

    @Deprecated
    public static Long setnx(String key,String value){
        Jedis jedis = null;
        Long result = null;
        try{
            jedis = getJedis();
            result = jedis.setnx(key,value);
        }catch (Exception e){
            log.error("redis setnx 异常 {}",key);
        }finally {
            release(jedis);
        }
        return result;
    }

    public static boolean unlock(String key,String value){
        return unlock1(key,value);
        //return unlock2(key,value);
    }

    /**
     * 官网推荐使用 Lua脚本来释放锁
     * @param key
     * @param value
     * @return
     */
    private static boolean unlock1(String key, String value) {
        Jedis jedis = null;
        boolean flag = false;
        try {
            jedis = getJedis();
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            Object result = jedis.eval(script, Collections.singletonList(key), Collections.singletonList(value));
            if (Objects.equals(UNLOCK_SUCCESS, result)) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(jedis);
        }
        return flag;
    }

    /**
     * 使用 watch 来检测获取的key有没有变动
     * @param key
     * @param value
     * @return
     */
    private static boolean unlock2(String key, String value) {
        Jedis jedis = null;
        boolean flag = false;
        try {
            jedis = getJedis();
            jedis.watch(key);
            String existValue = jedis.get(key);
            if (Objects.equals(value, existValue)) {
                jedis.del(key);
                flag = true;
            } else {
                log.error("unlock failed ; key: {},value: {},existValue: {}" ,key ,value ,existValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.unwatch();
            release(jedis);
        }
        return flag;
    }
}

