package com.zhliang.springboot.cache.cache;

import com.google.common.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/11 10:55
 * @version：V1.0
 */
public class LocalCacheService {

    @Autowired
    private Cache<String, String> localCache;

    public String get(String key){
        return localCache.getIfPresent(key);
    }

    public void set(String key,String value){
        localCache.put(key,value);
    }

    public void del(String key){
        localCache.invalidate(key);
    }

}
