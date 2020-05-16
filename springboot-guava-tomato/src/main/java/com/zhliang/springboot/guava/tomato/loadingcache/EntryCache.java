package com.zhliang.springboot.guava.tomato.loadingcache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.guava.tomato.loadingcache
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/12 12:30
 * @version：V1.0
 */
@Component
public class EntryCache {

    private static final Logger logger = LoggerFactory.getLogger(EntryCache.class);

    public static final Map<String,Entry> ioc = new HashMap<String, Entry>();

    //类初始化 执行的方法
    @PostConstruct
    public void init(){
        Entry entry = new Entry();
        ioc.put("1",entry);
    }

    /**
     * 基础使用
     *
     * 这里 refreshAfterWrite|expireAfterWrite的区别是：
     *     refreshAfterWrite: 当缓存记录到期后，不会直接删除记录。而是只有一个线程去请求DB更新缓存，其它请求直接返回老值。
     *     expireAfterWrite： 缓存记录到期后会直接删除缓存。如果有并发请求过来，会导致请求直接去读取DB。
     *
     * LoadingCache 缓存实体
     */
    LoadingCache<String, Entry> cache = CacheBuilder.newBuilder()
            // 缓存刷新时间
            .refreshAfterWrite(10, TimeUnit.MINUTES)
            // 设置缓存个数
            .maximumSize(500)
            .build(new CacheLoader<String, Entry>() {
                @Override
                // 当本地缓存命没有中时，调用load方法获取结果并将结果缓存
                public Entry load(String appKey) {
                    return getEntryFromDB(appKey);
                }
                // 数据库进行查询
                private Entry getEntryFromDB(String name) {
                    logger.info("load entry info from db!entry:{}", name);
                    return ioc.get(name);
                }
            });

    /**
     * 对外暴露的方法
     * 从缓存中取entry，没取到就走数据库
     */
    public Entry getEntry(String name) throws ExecutionException {
        return cache.get(name);
    }

}
