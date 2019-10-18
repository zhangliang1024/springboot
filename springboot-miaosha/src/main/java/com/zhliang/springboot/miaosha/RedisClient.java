package com.zhliang.springboot.miaosha;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author: colin
 * @Date: 2019/9/5 14:58
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Data
public class RedisClient {

    public JedisPool pool;

    public RedisClient(){}
    public RedisClient(JedisPool pool){
        this.pool = pool;
    }


    /**
     * 根据key来获取对象value
     * @param key
     * @return
     */
    public Object getByKey(String key){
        Jedis jedis = pool.getResource();
        String value = null;
        try{
            value = jedis.get(key);
        }finally{
            //向连接池归还"资源"
            pool.returnResource(jedis);
        }
        return value;
    }

    /**
     * 判断 String类型的 Key是否存在
     * @param key
     * @return
     */
    public boolean isKeyExist(String key){
        Jedis jedis = pool.getResource();
        boolean bool = false;
        try{
            bool = jedis.exists(key);
        }finally{
            //向连接池归还"资源"
            pool.returnResource(jedis);
        }
        return bool;
    }

    /**
     * String 类型的 键值写入redis
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key,String value){
        Jedis jedis = pool.getResource();
        try {
            String result = jedis.set(key, value);
            if("OK".equals(result)){
                return true;
            }else {
                return false;
            }
        } finally {
            pool.returnResource(jedis);
        }
    }


    /**
     * Redis setNx : 如果key不存在则插入
     * @param key
     * @param value
     * @return
     */
    public Long setnx(String key,String value){
        Jedis jedis = pool.getResource();
        try{
            Long result = jedis.setnx(key, value);
            return result;
        }finally {
            pool.returnResource(jedis);
        }
    }

    /**
     * String类型的键值写入Redis 并设置过期时间
     * @param key
     * @param value
     * @return
     */
    public boolean setKeyWithExpireTime(String key,String value,int expireTime){
        Jedis jedis = pool.getResource();
        try {
            String result = jedis.setex(key, expireTime, value);
            if("OK".equals(result)){
                return true;
            }else{
                return false;
            }
        }finally {
            pool.returnResource(jedis);
        }
    }


    public Long expire(String key, int expire) {
        Jedis client = pool.getResource();
        Long success = 1l;
        try{
            success = client.expire(key, expire);
        }finally{
            pool.returnResourceObject(client);
        }
        return success;
    }

    public boolean  delKey(String key) {
        Jedis client = pool.getResource();
        try {
            log.info("del key : {}" , key);
            client.del(key);
            return true;
        } finally {
            // 向连接池“归还”资源
            pool.returnResourceObject(client);
        }
    }
}
