package com.zhliang.springboot.redisson.base;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: colin
 * @Date: 2019/9/10 10:10
 * @Description:
 * @Version: V1.0
 */
//@Service
public class MockService {


    @Autowired
    private RedissonClient client;


    public boolean decrementProductStore(Long productId,Integer productQuantity){
        String key = "des_store_lock" + productId;
        //获取锁
        RLock lock = client.getLock(key);
        try{
            //加锁  机制很类似Java的ReentrantLock机制
            lock.lock();
            lock.lock(60, TimeUnit.SECONDS); //设置60秒的自动过期 (默认是 30秒自动过期)
            //模拟查询库存操作
            //如果库存 =< 0 直接返回false
            //否则 扣减库存  返回true
        }catch (Exception e){
            //记录异常
        }finally {
            //释放锁
            lock.unlock();
        }
        return true;
    }


}
