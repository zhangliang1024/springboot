package com.zhliang.springboot.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * SpringBoot 缓存：
 *  - SpringBoot 本身提供了基于ConcurrentHashMap的缓存机制
 *  - Springboot 通过注解方式来统一使用缓存，只需要在方法上使用缓存注解即可。其缓存的具体实现可依赖于你选择的目标缓存管理器
 *
 *
 * SpringBoot 支持的缓存管理器：
 *  - simple: 基于ConcurrentHashMap实现的缓存，适合单机或者开发环境使用。
 *  - none：关闭缓存，比如开发阶段先确保功能正确，可以先禁止使用缓存
 *  - redis：使用redis作为缓存，你还需要在pom里增加redis依赖。本章缓存将重点介绍redis缓存以及扩展redis实现一二级缓存
 *  - Generic，用户自定义缓存实现，用户需要实现一个org.springframework.cache.CacheManager的实现
 *  - 其他还有JCache，EhCache 2.x，Hazelcast等，为了保持本书的简单，将不在这里一一介绍
 *
 *
 *
 *
 *
 * 作者：小红牛
 * 链接：https://www.jianshu.com/p/f14750f1f80a
 * 来源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 *
 *
 *
 */


//打开缓存功能
@EnableCaching
@SpringBootApplication
public class SpringbootRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRedisApplication.class, args);
    }

}
