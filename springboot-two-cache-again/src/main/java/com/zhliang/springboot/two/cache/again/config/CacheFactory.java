package com.zhliang.springboot.two.cache.again.config;

import com.zhliang.springboot.two.cache.again.message.MessagePublisher;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.InputStream;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.two.cache.again.config
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/6 14:38
 * @version：V1.0
 */
@Component
public class CacheFactory {

    private static final Logger logger = LoggerFactory.getLogger(CacheFactory.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MessagePublisher messagePublisher;

    private CacheManager cacheManager;

    public CacheFactory() {
        InputStream is = this.getClass().getResourceAsStream("/ehcache.xml");
        if(is != null) {
            cacheManager = CacheManager.create(is);
        }
    }

    public void cacheDel(String name,String key) {
        //删除redis对应的缓存
        redisDel(name,key);
        //删除本地的ehcache缓存,可以不需要，订阅器那里会删除
        //   ehDel(name,key);
        if(cacheManager != null) {
            //发布一个消息，告诉订阅的服务该缓存失效
            messagePublisher.publish(name, key);
        }
    }

    public String ehGet(String name,String key) {
        if(cacheManager == null) return null;
        Cache cache=cacheManager.getCache(name);
        if(cache == null) return null;
        cache.acquireReadLockOnKey(key);
        try {
            Element ele = cache.get(key);
            if(ele == null) return null;
            return (String)ele.getObjectValue();
        } finally {
            cache.releaseReadLockOnKey(key);
        }


    }

    public String redisGet(String name,String key) {
        HashOperations<String,String,String> oper = redisTemplate.opsForHash();
        try {
            return oper.get(name, key);
        } catch(RedisConnectionFailureException e) {
            //连接失败，不抛错，直接不用redis缓存了
            logger.error("connect redis error ",e);
            return null;
        }
    }

    public void ehPut(String name,String key,String value) {
        if(cacheManager == null) return;
        if(!cacheManager.cacheExists(name)) {
            cacheManager.addCache(name);
        }
        Cache cache=cacheManager.getCache(name);
        //获得key上的写锁，不同key互相不影响，类似于synchronized(key.intern()){}
        cache.acquireWriteLockOnKey(key);
        try {
            cache.put(new Element(key, value));
        } finally {
            //释放写锁
            cache.releaseWriteLockOnKey(key);
        }
    }

    public void redisPut(String name,String key,String value) {
        HashOperations<String,String,String> oper = redisTemplate.opsForHash();
        try {
            oper.put(name, key, value);
        } catch (RedisConnectionFailureException e) {
            //连接失败，不抛错，直接不用redis缓存了
            logger.error("connect redis error ",e);
        }
    }

    public void ehDel(String name,String key) {
        if(cacheManager == null) return;
        Cache cache = cacheManager.getCache(name);
        if(cache != null) {
            //如果key为空，直接根据缓存名删除
            if(StringUtils.isEmpty(key)) {
                cacheManager.removeCache(name);
            } else {
                cache.remove(key);
            }
        }
    }

    public void redisDel(String name,String key) {
        HashOperations<String,String,String> oper = redisTemplate.opsForHash();
        try {
            //如果key为空，直接根据缓存名删除
            if(StringUtils.isEmpty(key)) {
                redisTemplate.delete(name);
            } else {
                oper.delete(name,key);
            }
        } catch (RedisConnectionFailureException e) {
            //连接失败，不抛错，直接不用redis缓存了
            logger.error("connect redis error ",e);
        }
    }

}
