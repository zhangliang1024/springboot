package com.zhliang.springboot.other.code;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: colin
 * @Date: 2019/9/9 11:34
 * @Description:  封装一个Redis连接池
 * @Version: V1.0
 */
public class RedisPool {

    /**Jedis连接池*/
    private static JedisPool pool;

    /**最大连接数*/
    private static int maxTotal = 20;

    /**最大空闲连接数*/
    private static int maxIdle = 10;

    /**最小空闲连接数*/
    private static int minIdle = 5;

    /**在取连接时 测试连接的可用性*/
    private static boolean testOnBorrow = true;

    /**在还连接时 不测试连接的可用性*/
    private static boolean testOnReturn = false;


    static {
        initPool();
    }

    private static void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnBorrow);
        config.setBlockWhenExhausted(true);
        pool = new JedisPool(config, "127.0.0.1");
    }

    public static Jedis getJedis() {
        return pool.getResource();
    }

    public static void close(Jedis jedis){
        jedis.close();
    }
}
