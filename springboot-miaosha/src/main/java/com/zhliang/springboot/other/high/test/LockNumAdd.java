package com.zhliang.springboot.other.high.test;
import com.zhliang.springboot.other.high.LockUtil;

import	java.util.concurrent.CountDownLatch;

/**
 * @Author: colin
 * @Date: 2019/9/9 17:17
 * @Description:
 * @Version: V1.0
 */
public class LockNumAdd extends NumAdd {

    final CountDownLatch latch ;


    public LockNumAdd(int threadCount){
        resetNum();
        latch = new CountDownLatch(threadCount);
    }


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            String key = "test-key";
            String value = LockUtil.getLockValue();
            LockUtil.lock(key,value);
            try{
                addNum();
            }finally {
                LockUtil.unlock(key, value);
            }
        }
        latch.countDown();
    }
}
