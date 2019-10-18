package com.zhliang.springboot.miaosha;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author: colin
 * @Date: 2019/9/5 14:55
 * @Description:
 * @Version: V1.0
 */
public class RedisFactory {

    public static JedisPoolConfig getPoolConfig() throws IOException {
        Properties properties = new Properties();
        InputStream in = RedisFactory.class.getClassLoader().getResourceAsStream("redis.properties");

        try {
            properties.load(in);
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMinIdle(Integer.parseInt(properties.getProperty("minIdle","100")));
            config.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle", "1")));
            config.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal", "1000")));
            return config;
        }finally {
            in.close();
        }
    }

    public static RedisClient getDefaultClient(){
        JedisPool pool = new JedisPool("127.0.0.1");
        RedisClient client = new RedisClient(pool);
        return client;
    }
}
