package com.zhliang.springboot.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.redis.config
 * @类描述：支持一二级缓存，使得性能到达极致,
 * @创建人：colin
 * @创建时间：2019/12/3 19:22
 * @version：V1.0
 */
@Configuration
public class CacheConfig {

    /**
     * 定义一个redis 的频道，默认叫cache，用于pub/sub
     */
    @Value("${springext.cache.redis.topic:cache}")
    private String topicName;

    /**
     * 将缓存管理器配置为应用的缓存管理器
     */
    @Bean
    public TwoLevelCacheManager cacheManager(StringRedisTemplate redisTemplate) {
        //RedisCache需要一个RedisCacheWriter来实现读写Redis
        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(redisTemplate.getConnectionFactory());
        /*
        * SerializationPair用于Java和Redis之间的序列化和反序列化，我们这里使用自带的JdkSerializationRedisSerializer,
        * 并在反序列化过程中，使用当前的ClassLoader
        */
        RedisSerializationContext.SerializationPair pair = RedisSerializationContext.SerializationPair.fromSerializer(new JdkSerializationRedisSerializer(this.getClass().getClassLoader()));
        /*构造一个RedisCache的配置，比如是否使用前缀，比如Key和Value的序列化机制（*/
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
        /*创建CacheManager，并返回给Spring 容器*/
        TwoLevelCacheManager cacheManager = new TwoLevelCacheManager(redisTemplate,writer,config);
        return cacheManager;
    }

    /**
     * Redis message，参考Redis一章 ： 实现Redis的Pub/Sub 模式
     *  - 需要创建一个消息监听器
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic(topicName));
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(final TwoLevelCacheManager cacheManager) {

        return new MessageListenerAdapter(new MessageListener() {

            public void onMessage(Message message, byte[] pattern) {
                byte[] bs = message.getChannel();
                try {
                    // Sub 一个消息，通知缓存管理器
                    String type = new String(bs, "UTF-8");
                    String cacheName = new String(message.getBody(),"UTF-8");
                    cacheManager.receiver(cacheName);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    // 不可能出错，忽略
                }
            }
        });
    }

    /**
     * 缓存管理器  -> 扩展自：RedisCacheManager
     */
    class TwoLevelCacheManager extends RedisCacheManager {

        RedisTemplate redisTemplate;

        public TwoLevelCacheManager(RedisTemplate redisTemplate,RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
            super(cacheWriter, defaultCacheConfiguration);
            this.redisTemplate = redisTemplate;
        }

        /**
         * 使用RedisAndLocalCache代替Spring Boot自带的RedisCache
         */
        @Override
        protected Cache decorateCache(Cache cache) {
            return new RedisAndLocalCache(this, (RedisCache) cache);
        }

        //当缓存更新的时候: 使用Redis的消息机制通知 其他分布式节点，缓存改变
        public void publishMessage(String cacheName) {
            this.redisTemplate.convertAndSend(topicName, cacheName);
        }

        /**
         * 接受一个消息清空本地缓存
         *  - 清空后的一级缓存会在下次get方法调用时在从RedisCache中获取并缓存起来
         */

        public void receiver(String name) {
            RedisAndLocalCache cache = ((RedisAndLocalCache) this.getCache(name));
            if(cache!=null){
                cache.clearLocal();
            }
        }

    }

    /**
     * 实现了Cache接口
     */
    class RedisAndLocalCache implements Cache {

        // 本地缓存提供
        ConcurrentHashMap<Object, Object> local = new ConcurrentHashMap<Object, Object>();
        RedisCache redisCache;
        TwoLevelCacheManager cacheManager;

        public RedisAndLocalCache(TwoLevelCacheManager cacheManager, RedisCache redisCache) {
            this.redisCache = redisCache;
            this.cacheManager = cacheManager;
        }

        @Override
        public String getName() {
            return redisCache.getName();
        }

        @Override
        public Object getNativeCache() {
            return redisCache.getNativeCache();
        }

        /**
         * get(): 通过对应KEY获取到对应缓存
         *   - 在调用RedisCache之前会先从本地缓存中查询是否存在
         */
        @Override
        public ValueWrapper get(Object key) {
            ValueWrapper wrapper = (ValueWrapper) local.get(key);
            if (wrapper != null) {
                return wrapper;
            } else {
                // 二级缓存取
                wrapper = redisCache.get(key);
                if (wrapper != null) {
                    local.put(key, wrapper);
                }
                return wrapper;
            }
        }

        /**
         * put()：调用RedisCache put()方法同时广播消息，改变其他一级缓存
         * @param key
         * @param value
         */
        @Override
        public void put(Object key, Object value) {
            System.out.println(value.getClass().getClassLoader());
            redisCache.put(key, value);
            //通知其他节点缓存更新
            clearOtherJVM();
        }

        public ValueWrapper putIfAbsent(Object key, Object value) {
            ValueWrapper v = redisCache.putIfAbsent(key, value);
            clearOtherJVM();
            return v;
        }

        @Override
        public void evict(Object key) {
            redisCache.evict(key);
            clearOtherJVM();
        }

        @Override
        public <T> T get(Object key, Class<T> type) {
            return redisCache.get(key, type);
        }

        @Override
        public <T> T get(Object key, Callable<T> valueLoader) {
            return redisCache.get(key, valueLoader);
        }

        @Override
        public void clear() {
            redisCache.clear();
        }

        // 提供给CacheManager清空一节缓存
        public void clearLocal() {
            this.local.clear();
        }

        protected void clearOtherJVM() {
            cacheManager.publishMessage(redisCache.getName());
        }
    }
}
