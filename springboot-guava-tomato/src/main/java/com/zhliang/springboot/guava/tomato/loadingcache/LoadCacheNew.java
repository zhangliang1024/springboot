package com.zhliang.springboot.guava.tomato.loadingcache;

import com.google.common.cache.*;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.guava.tomato.loadingcache
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/12 13:59
 * @version：V1.0
 */
public class LoadCacheNew {

    /**
     * Guava LoadingCache使用记录：
     *  https://blog.csdn.net/sunquan291/article/details/78599114
     */

    static List<String> list = Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "j", "k", "m");

    private static String compute(Integer key) {
        System.out.println(Thread.currentThread().getName() + "-compute value with " + key);
        return list.get(key);
    }


    public static void main(String[] args) throws Exception {
        //testCache1();
        testCache3();
        //testCache2();
        //testCache4();
        //testCache5();
        //testCache6();
        //testCache7();
    }

    public static void testCache1() throws ExecutionException {
        Cache<Integer, String> cache = CacheBuilder.newBuilder().build();
        cache.put(0, "a");
        cache.put(1, "b");
        cache.put(2, "c");
        cache.put(3, "d");
        System.out.println(cache.getIfPresent(0));//a
        System.out.println(cache.getIfPresent(-1));//null
        System.out.println(cache.get(-1, () -> "bb"));//bb （无指定key的value，则执行callable,并将计算值加入缓存）
        System.out.println(cache.getIfPresent(-1));//bb
    }

    public static void testCache3() throws ExecutionException, InterruptedException {
        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
                .build(new CacheLoader<Integer, String>() {
                    //cache for what
                    public String load(Integer key) {
                        return compute(key);
                    }
                });
        //每隔1秒进行数据更新，会执行compute重新计算
        ScheduledExecutorService exe = Executors.newScheduledThreadPool(1);
        exe.scheduleAtFixedRate(() -> cache.asMap().keySet().forEach(cache::refresh), 0, 1, TimeUnit.SECONDS);
        System.out.println("v1:" + cache.get(1));
        cache.invalidate(1);//消除key为1的缓存
        Thread.sleep(2000);
        System.out.println("v2:" + cache.get(1));//重新执行compute
    }

    public static void testCache2() throws ExecutionException, InterruptedException {
        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
                .build(new CacheLoader<Integer, String>() {
                    //cache for what
                    public String load(Integer key) {
                        return compute(key);
                    }
                });
        //每隔1秒进行数据更新，会执行compute重新计算
        ScheduledExecutorService exe = Executors.newScheduledThreadPool(1);
        exe.scheduleAtFixedRate(() -> cache.asMap().keySet().forEach(cache::refresh), 0, 1, TimeUnit.SECONDS);
        System.out.println("v1:" + cache.get(1));
        Thread.sleep(1000);
        System.out.println("v2:" + cache.get(1));
        Thread.sleep(5000);
    }


    public static void testCache4() throws ExecutionException, InterruptedException {
        //增加缓存值删除监听器
        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder().removalListener(new RemovalListener<Integer, String>() {
                    @Override
                    public void onRemoval(RemovalNotification<Integer, String> removalNotification) {
                        System.out.println(Thread.currentThread().getName() + "-remove key:" + removalNotification.getKey());
                        System.out.println(Thread.currentThread().getName() + "-remove value:" + removalNotification.getValue());
                    }
                })
                .build(new CacheLoader<Integer, String>() {
                    //cache for what
                    public String load(Integer key) {
                        return compute(key);
                    }
                });
        //每隔1秒进行数据更新，会执行compute重新计算（注意refresh会触发删除操作)
        ScheduledExecutorService exe = Executors.newScheduledThreadPool(1);
        exe.scheduleAtFixedRate(() -> cache.asMap().keySet().forEach(cache::refresh), 0, 1, TimeUnit.SECONDS);
        System.out.println("v1:" + cache.get(1));
        Thread.sleep(1000);
        System.out.println("v2:" + cache.get(1));
        Thread.sleep(3000);

        cache.invalidateAll();//invalidate亦会触发删除操作
        Thread.sleep(3000);
    }

    public static void testCache5() throws ExecutionException, InterruptedException {
        //增加缓存值删除监听器
        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder().removalListener(new RemovalListener<Integer, String>() {
                    @Override
                    public void onRemoval(RemovalNotification<Integer, String> removalNotification) {
                        System.out.println(Thread.currentThread().getName() + "-remove key:" + removalNotification.getKey());
                        System.out.println(Thread.currentThread().getName() + "-remove value:" + removalNotification.getValue());
                    }
                }).recordStats()
                .build(new CacheLoader<Integer, String>() {
                    //cache for what
                    public String load(Integer key) {
                        return compute(key);
                    }
                });
        //每隔1秒进行数据更新，会执行compute重新计算（注意refresh会触发删除操作)
        ScheduledExecutorService exe = Executors.newScheduledThreadPool(1);
        exe.scheduleAtFixedRate(() -> cache.asMap().keySet().forEach(cache::refresh), 0, 1, TimeUnit.SECONDS);
        System.out.println("v1:" + cache.get(1));
        Thread.sleep(1000);
        System.out.println("v2:" + cache.get(1));
        Thread.sleep(3000);

        cache.invalidateAll();//invalidate亦会触发删除操作
        Thread.sleep(3000);
        System.out.println(cache.stats());
    }

    public static void testCache6() throws ExecutionException, InterruptedException {
        RemovalListener<Integer, String> listener = RemovalListeners.asynchronous(new RemovalListener<Integer,String>() {
            @Override
            public void onRemoval(RemovalNotification<Integer, String> removalNotification) {
                System.out.println(Thread.currentThread().getName() + "-remove key:" + removalNotification.getKey());
                System.out.println(Thread.currentThread().getName() + "-remove value:" + removalNotification.getValue());
            }
        }, Executors.newSingleThreadExecutor());
        //增加缓存值删除异步监听器
        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
                .removalListener(listener).recordStats()
                .build(new CacheLoader<Integer, String>() {
                    //cache for what
                    public String load(Integer key) {
                        return compute(key);
                    }
                });
        //每隔1秒进行数据更新，会执行compute重新计算（注意refresh会触发删除操作)
        ScheduledExecutorService exe = Executors.newScheduledThreadPool(1);
        exe.scheduleAtFixedRate(() -> cache.asMap().keySet().forEach(cache::refresh), 0, 1, TimeUnit.SECONDS);
        System.out.println("v1:" + cache.get(1));
        Thread.sleep(1000);
        System.out.println("v2:" + cache.get(1));
        Thread.sleep(3000);

        cache.invalidateAll();//invalidate亦会触发删除操作
        Thread.sleep(3000);
        System.out.println(cache.stats());
        cache.cleanUp();
    }

    public static void testCache7() throws ExecutionException, InterruptedException {
        ScheduledExecutorService exe = Executors.newScheduledThreadPool(1);
        //增加缓存值删除异步监听器
        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
                .build(new CacheLoader<Integer, String>() {
                    //cache for what
                    public String load(Integer key) {
                        return compute(key);
                    }
                });
        //每隔5分钟进行数据更新，会执行compute重新计算（注意refresh会触发删除操作)
        exe.scheduleAtFixedRate(() -> cache.asMap().keySet().forEach(cache::refresh), 0, 1, TimeUnit.MINUTES);
        System.out.println("v1:" + cache.get(1));
        Thread.sleep(1000);
        System.out.println("v2:" + cache.get(1));
        Thread.sleep(3000);
        cache.invalidateAll();//invalidate亦会触发删除操作
        Thread.sleep(3000);
        cache.cleanUp();
    }
}
