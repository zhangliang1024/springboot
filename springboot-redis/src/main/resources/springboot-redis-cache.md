# SpringBoot Redis Cache
### @Cacheable、@CacheEvict、@CachePut 的使用

### CacheManager 缓存管理器(管各种缓存组件)
```java
 @Bean
public CacheManager cacheManager(RedisConnectionFactory factory) {
    RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofSeconds(60))
            .disableCachingNullValues();

    return RedisCacheManager.builder(factory)
            .cacheDefaults(config)
            .transactionAware()
            .build();
}
————————————————
原文链接：https://blog.csdn.net/u010588262/article/details/81003493
```

### 参考博客

* [SpringBoot2.0.3 Redis缓存 @Cacheable、@CacheEvict、@CachePut](https://blog.csdn.net/u010588262/article/details/81003493)
* [SpringBoot进阶教程(五十三)整合Redis之@Cacheable、@CachePut、@CacheEvict的应用](https://www.cnblogs.com/toutou/p/cacheable.html)
* [springboot+redis实现缓存数据](https://www.cnblogs.com/hhhshct/p/9002648.html)

### Guides



