package com.zhliang.springboot.other.high;

import lombok.extern.slf4j.Slf4j;


/**
 * @Author: colin
 * @Date: 2019/9/9 15:35
 * @Description: --  基于Redis setnx实现的分布式锁，前提是所有的锁都有过期时间
 * --  获取锁的时候，需要指定value. 在unlock的时候，会根据value判断是否remove
 * -----------------------------------------------------------------------------------
 * ++ 基于redis setnx的简易分布式锁(修正版) ： https://blog.csdn.net/qq315737546/article/details/79728676
 * -----------------------------------------------------------------------------------
 * @Version: V1.0
 */
@Slf4j
public class LockUtil {

    private static final String LOCK_PREFIX = "LOCK";
    /**
     * 默认锁定时间：秒
     */
    private static Integer DEFAULT_LOCK_TIME = 20;
    /**
     * 默认sleep时间 ：100毫秒
     */
    private static Long DEFAULT_SLEEP_TIME = 100L;


    /**
     * 获取缓存的value值：这里用随机值 不同的锁value值不同
     * - 分布式环境(多服务器)，可以使用redis时间 + 客户端唯一标识
     * @return
     */
    public static String getLockValue(){
        int random = (int)((Math.random() * 9 + 1) * 100000);
        long now = System.currentTimeMillis();
        return String.valueOf(random) + String.valueOf(now);
    }

    /**
     * 获取锁，如果失败自动重试
     * @param key
     * @param value
     * @return
     */
    public static boolean lock(String key,String value) {
        return lock(key, value, DEFAULT_LOCK_TIME);
    }

    private static boolean lock(String key, String value, Integer defaultLockTime) {
        return lock(key, value, DEFAULT_LOCK_TIME,true);
    }

    /**
     * 获取锁 如果失败，直接返回false
     * @param key
     * @param value
     * @return
     */
    public static boolean tryLock(String key,String value){
        return tryLock(key, value, DEFAULT_LOCK_TIME);
    }

    /**
     * 获取锁 如果失败，直接返回false
     * @param key
     * @param value
     * @param lockTime
     * @return
     */
    public static boolean tryLock(String key,String value,long lockTime){
        return lock(key,value,lockTime,false);
    }

    private static boolean lock(String key, String value, long lockTime, boolean reTry) {
        return lock(key,value,lockTime,reTry,0,false,0);
    }

    /**
     * 尝试获取锁：如果失败，重试，知道成功或者超出指定时间
     * @param key
     * @param value
     * @param lockTime       锁过期时间
     * @param timeOutMillis  重试超时时间
     * @return
     */
    public static boolean tryLock(String key,String value,long lockTime,long timeOutMillis){
        return lock(key,value,lockTime,true,0,true,timeOutMillis);
    }

    /**
     * 释放锁 ： key对应的value与参数一致才删除锁
     * @param key
     * @param value
     * @return
     */
    public static boolean unlock(String key,String value){
        String fullKey = getFullKey(key);
        boolean success = JedisUtil.unlock(key, value);
        if(success){
            log.info("unlock success");
        }else {
            log.error("unlock error");
        }
        return success;
    }

    /**
     * 加锁的主要方法
     *
     * @param key           redis key
     * @param value         redis value
     * @param lockTime      锁定时间，即设置的超时时间
     * @param reTry         失败是否重试
     * @param curTryCount   重试次数
     * @param needTimeOut   是否判断超时 时间
     * @param timeOutMillis 尝试超时时间（毫秒）
     * @return
     */
    private static boolean lock(String key, String value, long lockTime, boolean reTry, int curTryCount,
                                boolean needTimeOut
            , long timeOutMillis) {
        log.info(Thread.currentThread().getName() + ",lock come in ; key: {},value: {},lockTime: {},reTry: {}," +
                        "curTryCount: {},needTimeOut: {},timeOutMillis: {}", key, value,
                lockTime, reTry, curTryCount, needTimeOut, timeOutMillis);

        curTryCount++;
        String fullKey = getFullKey(key);

        // set nx px
        boolean success = JedisUtil.set(key, value, lockTime * 1000);
        //获取成功直接返回
        if (success) {
            return true;
        }
        //获取失败，不需要重试 直接返回
        if (!reTry) {
            return false;
        }
        //获取失败，且已超时 直接返回
        if (needTimeOut && timeOutMillis <= 0) {
            return false;
        }
        //获取休眠时间
        long sleepMillis = getSleepMillis(needTimeOut, timeOutMillis);

        //sleep 后重新获取锁
        sleep(sleepMillis);

        //大于100次打印 warn日志
        if(curTryCount > 100){
            log.warn("lock warn in ; key: {},value: {},lockTime: {},reTry: {}," +
                            "curTryCount: {},needTimeOut: {},timeOutMillis: {}", key, value,
                    lockTime, reTry, curTryCount, needTimeOut, timeOutMillis);
        }
        return lock(key, value, lockTime, reTry, curTryCount, needTimeOut, timeOutMillis);
    }

    private static void sleep(long sleepMillis) {
        try {
            Thread.sleep(sleepMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static long getSleepMillis(boolean needTimeOut, long timeOutMillis) {
        long sleepMillis = DEFAULT_SLEEP_TIME;
        if (needTimeOut) {
            timeOutMillis = timeOutMillis - sleepMillis;
            if (timeOutMillis < sleepMillis) {
                sleepMillis = timeOutMillis;
            }
        }
        return sleepMillis;
    }

    private static String getFullKey(String key) {
        return LOCK_PREFIX + ":" + key;
    }


}
