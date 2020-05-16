package com.zhliang.springboot.guava.tomato.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.concurrent.ExecutionException;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.guava.tomato.cache
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/12 11:20
 * @version：V1.0
 */
public class StudyLoadingCache {

    /**
     * LoadingCache 是Cache的子接口，从LoadingCache中读取一个指定的key时，如果记录不存在，LoadingCache可以自动
     *              执行加载数据到缓存的操作。
     *
     * 在使用LoadinCache时必须指定一个CacheLoader类型参数。CacheLoader实现了当我们缓存中没有记录时，CacheLoader
     *     的laod方法会被自动调用来执行加载数据的逻辑，load方法的返回值会做为key对应的value保存到LoadingCache中，
     *     并从get方法返回。
     *
     */

    public static void main(String[] args) throws ExecutionException {

        LoadingCache<String,String> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        Thread.sleep(1000); //休眠1s，模拟加载数据
                        System.out.println(key + " is loaded from a cacheLoader!");
                        return key + "'s value";
                    }
                });

        loadingCache.get("key1");
        loadingCache.get("key2");
        loadingCache.get("key3");
    }
}
