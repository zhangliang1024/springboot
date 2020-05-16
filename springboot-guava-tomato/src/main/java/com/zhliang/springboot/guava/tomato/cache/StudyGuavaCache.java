package com.zhliang.springboot.guava.tomato.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.guava.tomato.cache
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/12 10:47
 * @version：V1.0
 */
public class StudyGuavaCache {

    /**
     * 采用 CacheBuilder来构建一个缓存对象，CacheBuilder使用builder设计模式，它的每个方法都返回CacheBuilder本身，直到builder方法被调用
     */

    public static void main(String[] args) throws InterruptedException {
        //builder();
        //builderMaxSize();
        //builderExpireWrite();
        //builderExpireAccess();
        //builderWeak();
        //builderInvalidate();

        builderRemoveListener();
        builderStats();

        return;
    }

    /**
     * 可以对Cache的命中率、加载数据时间等信息进行统计
     * 统计信息
     */
    private static void builderStats() {
        Cache<String,String> cache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .recordStats() //开启统计信息开关
                .build();
        cache.put("key1","value1");
        cache.put("key2","value2");
        cache.put("key3","value3");
        cache.put("key4","value4");

        cache.getIfPresent("key1");
        cache.getIfPresent("key2");
        cache.getIfPresent("key3");
        cache.getIfPresent("key4");
        cache.getIfPresent("key5");
        cache.getIfPresent("key6");

        System.out.println(cache.stats()); //获取统计信息
    }

    /**
     * Cache可以添加一个移除监听器 当有记录被移除时可以感知到这个事件
     * 移除监听器
     */
    private static void builderRemoveListener() {
        RemovalListener<String,String> listener = new RemovalListener<String,String>(){
            @Override
            public void onRemoval(RemovalNotification<String, String> notification) {
                System.out.println("[" + notification.getKey() + ":" + notification.getValue() + "] is removed!");
            }
        };
        Cache<String,String> cache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .removalListener(listener)
                .build();
        Object value = new Object();
        cache.put("key1","value1");
        cache.put("key2","value2");
        cache.put("key3","value3");
        cache.put("key4","value3");
        cache.put("key5","value3");
        cache.put("key6","value3");
        cache.put("key7","value3");
        cache.put("key8","value3");
    }

    /**
     * Cache的invalidateAll|invalidate方法可以显示的清除缓存中的记录。 invalidateAll可以批量删除记录，当没有任何参数时会删除所有的记录。
     * 缓存显示 清除
     */
    private static void builderInvalidate() {
        Cache<String,String> cache = CacheBuilder.newBuilder().build();
        Object value = new Object();
        cache.put("key1","value1");
        cache.put("key2","value2");
        cache.put("key3","value3");

        List<String> list = new ArrayList<String>();
        list.add("key1");
        list.add("key2");

        cache.invalidateAll(list);//批量清除list中全部key对应的记录
        System.out.println(cache.getIfPresent("key1"));
        System.out.println(cache.getIfPresent("key2"));
        System.out.println(cache.getIfPresent("key3"));
    }

    /**
     * CacheBuilder可以通过 weakKeys|weakValues方法指定Cache只保存对缓存记录key\value的弱引用。这样当没有其它强引用指向key value时，就可以被垃圾回收器收回
     * 弱引用
     */
    private static void builderWeak() {
        Cache<String,Object> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
                .weakValues()
                .build();
        Object value = new Object();
        cache.put("key1",value);

        value = new Object();//原对象不再有强引用
        System.gc();
        System.out.println(cache.getIfPresent("key1"));
    }

    private static void builderExpireAccess() throws InterruptedException {
        Cache<String,String> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
                .expireAfterAccess(3, TimeUnit.SECONDS)
                .build();
        cache.put("key1","value1");
        int time = 1;
        while(true) {
            Thread.sleep(time*1000);
            System.out.println("睡眠" + time++ + "秒后取到key1的值为：" + cache.getIfPresent("key1"));
        }
    }

    /**
     * CacheBuilder在构建时可以通过：expireAfterWirte|expireAfterAccess两个方法为缓存中的对象设置过期时间，过期的对象将被自动删除
     *   expireAfterWrite: 指对象被写入到缓存后多久删除
     *   expierAfterAccess: 值对象被写入缓存后多久 没有被访问后删除
     * 设置过期时间
     */
    private static void builderExpireWrite() throws InterruptedException {
        Cache<String,String> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .build();
        cache.put("key1","value1");
        int time = 1;
        for (;;) {
            System.out.println("第" + time++ + "次取到key1的值为：" + cache.getIfPresent("key1"));
            Thread.sleep(1000);
        }
    }

    /**
     * Cache 可以在构建缓存对象时指定缓存能够 存储的最大记录数。当达到最大记录数，在put时Guava会从当前缓存记录中选择一条删除，腾出空间来保存新记录
     * 设置最大存储
     */
    private static void builderMaxSize() {
        Cache<String,String> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
                .build();
        cache.put("key1","value1");
        cache.put("key2","value2");
        cache.put("key3","value3");
        System.out.println("第一个值：" + cache.getIfPresent("key1"));
        System.out.println("第二个值：" + cache.getIfPresent("key2"));
        System.out.println("第三个值：" + cache.getIfPresent("key3"));
    }

    /**
     * 采用 CacheBuilder来构建一个缓存对象，CacheBuilder使用builder设计模式，它的每个方法都返回CacheBuilder本身，直到builder方法被调用
     *
     * 构建缓存对象
     */
    private static void builder() {
        Cache<String,String> cache = CacheBuilder.newBuilder().build();
        cache.put("word","Hello Guava Cache");
        System.out.println(cache.getIfPresent("word"));
    }


}
