package com.zhliang.springboot.miaosha;
import lombok.extern.slf4j.Slf4j;

import	java.util.Random;

/**
 * @Author: colin
 * @Date: 2019/9/5 16:02
 * @Description:
 * @Version: V1.0
 */
@Slf4j
public class RedisLock {

    /**
     * 纳秒和毫秒之间的转换率
     */
    public static final long MILLI_NANO_TIME = 100 * 1000L;

    public static final String LOCKED = "TRUE";

    public static final Random RANDOM = new Random();

    private boolean lock = true;

    /**
     * 封装操作redis的工具
     */
    private RedisClient client;

    private String key;


    /**
     * @param purpose  锁前缀
     * @param key      锁定的ID东西
     */
    public RedisLock(String purpose,String key){
        this.key = purpose + "_" + key + "_lock" ;
        this.client = RedisFactory.getDefaultClient();
    }

    public RedisLock(String purpose,String key,RedisClient client){
        this.key = purpose + "_" + key + "_lock" ;
        this.client = RedisFactory.getDefaultClient();
        this.client = client;
    }


    /**
     * 加锁的方式：
     * lock();
     * try{
     *     executeMethod();
     * }finally{
     *     unLock();
     * }
     */


    /**
     * @param timeout  timeout时间范围内轮询锁
     * @param expire   设置锁的过期时间
     * @return
     */
    public boolean lock(long timeout,int expire){
        long nanoTime = System.nanoTime();
        try{
            // 在timeout的时间范围内不断轮询锁
            while(System.nanoTime() - nanoTime < timeout){
                //若 锁不存在 设置锁并设置过期时间 加锁成功
                if(client.setnx(key,LOCKED) == 1){
                    //设置超时时间 是为了在服务宕机后没有释放锁的情况下，不会造成死锁
                    client.expire(key, expire);
                    this.lock = true;
                    return this.lock;
                }
                log.info("加锁出现等待...");
                Thread.sleep(3,RANDOM.nextInt(30));
            }
        }catch (Exception e){
            throw new RuntimeException("locking error",e);
        }
        return false;
    }



    public void unLock(){
        try {
            if(this.lock){
                // 直接删除 key 即释放锁
                client.delKey(key);
            }
        }catch (Exception e){
            throw new RuntimeException("locking error",e);
        }
    }












}
