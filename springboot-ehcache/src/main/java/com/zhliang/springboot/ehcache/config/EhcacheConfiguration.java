package com.zhliang.springboot.ehcache.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.ehcache.config
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/9/15 14:03
 * @version：V1.0
 */

@Configuration
@EnableCaching
public class EhcacheConfiguration {

    @Bean
    public EhCacheManagerFactoryBean cacheManagerFactoryBean(){
        EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
        bean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        bean.setShared(true);
        return bean;
    }
    @Bean
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean){
        return new EhCacheCacheManager(bean.getObject());
    }

}
