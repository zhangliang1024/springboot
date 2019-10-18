package com.zhliang.springboot.other.code;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: colin
 * @Date: 2019/9/9 12:48
 * @Description: 分布式锁工具
 *   参考文档：
 *   ------------------------------------------------------------------
 *   + 分布式锁看这篇就够了: https://blog.csdn.net/NRlovestudy/article/details/82432972
 *   + 分布式锁的作用及实现（Redis）: https://blog.csdn.net/L_BestCoder/article/details/79336986
 *   ------------------------------------------------------------------
 * @Version: V1.0
 */
@Slf4j
public class DistributedLockUtil {


    private DistributedLockUtil(){}


    /**
     * 加锁
     * @param lockName ：可以为共享变量名、也可以为方法名。主要用于模拟锁信息
     * @return
     */
    public static boolean lock(String lockName){
        long beginTime = System.currentTimeMillis();
        log.info("开始尝试加锁：{}",beginTime);
        Long result = RedisPoolUtil.setnx(lockName, String.valueOf(beginTime + 5000));
        //获取到锁
        if(result != null && result.longValue() == 1){
            log.info("{} - 加锁成功 - {}", Thread.currentThread().getName(), lockName);
            //设置过期时间
            RedisPoolUtil.expire(lockName, 5);
            log.info("执行业务逻辑...");
            //执行完成后删除key
            RedisPoolUtil.del(lockName);
            return true;
        }else {
            //获取锁失败
            String value = RedisPoolUtil.get(lockName);

            //当前key有值，且已过期
            if(value !=  null && Long.parseLong(value) < System.currentTimeMillis()){
                String newValue = RedisPoolUtil.getSet(lockName, String.valueOf(System.currentTimeMillis() + 5000));
                //判断是否加锁成功
                if(newValue != null && newValue.equals(value)){
                    log.info("{} - 加锁成功 - {}", Thread.currentThread().getName(), lockName);
                    RedisPoolUtil.expire(lockName, 5);
                    log.info("执行业务逻辑..");
                    RedisPoolUtil.del(lockName);
                    return true;
                }else {
                    //已被其他请求加锁
                    return false;
                }
            }else {
                //未过期
                return false;
            }
        }
    }

    /**
     * 释放锁：比较锁设置的过期时间和业务处理时间
     *        如果业务处理时间小于锁的过期时间，则需要手动释放锁
     *        如果大于 则不需要，锁已经自动释放
     *
     * 问题：这里释放锁时，只判断了时间。没有判断是否是当前请求的锁
     * @param lockName
     */
    public static void unlock(String lockName){
        //获取锁的过期时间
        String value = RedisPoolUtil.get(lockName);
        if(value !=  null && Long.parseLong(value) > System.currentTimeMillis()){
            RedisPoolUtil.del(lockName);
        }
    }

}
