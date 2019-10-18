package com.zhliang.springboot.lock.controller;

import cn.gjing.lock.AbstractLock;
import cn.gjing.lock.Lock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: colin
 * @Date: 2019/8/21 19:52
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@RestController
public class LockController {

    private static int num = 20;

    /** TODO
     *  Lock 参数解析：
     *  key :   锁的唯一标识
     *  expire: 锁过期时间,单位：秒; 默认：5
     *  timeout:尝试获取锁的时间,单位：毫秒; 默认：500
     *  retry:  重新获取锁的间隔时间,单位：毫秒; 默认：10
     */
    @GetMapping("lock")
    @Lock(key = "lock")
    public void hello(){
        log.info("当前线程：{}",Thread.currentThread().getName());
        if(num == 0){
            log.info("卖完了！");
            return;
        }
        num --;
        log.info("剩余：{}",num);
    }

    @Resource
    private AbstractLock abstractLock;

    @GetMapping("doLock")
    public void doLock(){
        String lock = null;
        try{
            lock = abstractLock.lock("doLock",20,10000,50);
            log.info("当前线程：{}",Thread.currentThread().getName());
            if(num == 0){
                log.info("卖完了！");
                return;
            }
            num --;
            log.info("剩余：{}",num);
        }finally{
            this.abstractLock.release("doLock",lock);
        }
    }

}
