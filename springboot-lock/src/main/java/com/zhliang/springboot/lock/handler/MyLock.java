package com.zhliang.springboot.lock.handler;

import cn.gjing.lock.AbstractLock;
import org.springframework.stereotype.Component;

/**
 * @Author: colin
 * @Date: 2019/8/23 15:38
 * @Description: 可以实现自定义的锁实现
 * @Version: V1.0
 */
@Component
public class MyLock extends AbstractLock {

    @Override
    public String lock(String s, int i, int i1, int i2) {
        return null;
    }

    @Override
    public String release(String s, String s1) {
        return null;
    }
}
