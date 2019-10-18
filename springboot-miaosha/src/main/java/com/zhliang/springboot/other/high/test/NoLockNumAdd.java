package com.zhliang.springboot.other.high.test;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: colin
 * @Date: 2019/9/9 17:23
 * @Description:
 * @Version: V1.0
 */
public class NoLockNumAdd extends NumAdd {

    final CountDownLatch latch ;


    public NoLockNumAdd(int threadCount){
        resetNum();
        latch = new CountDownLatch(threadCount);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            addNum();
        }
        latch.countDown();
    }
}
