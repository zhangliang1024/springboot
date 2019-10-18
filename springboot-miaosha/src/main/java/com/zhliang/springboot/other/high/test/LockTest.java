package com.zhliang.springboot.other.high.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: colin
 * @Date: 2019/9/9 17:24
 * @Description:
 * @Version: V1.0
 */
public class LockTest {

    public static void main(String[] args) throws InterruptedException {
        int notLockAdd = testNoLockAdd();
        int lockAdd = testLockAdd();
        System.out.println("notLockAdd = " + notLockAdd);
        System.out.println("lockAdd = " + lockAdd);
    }


    private static int testLockAdd() throws InterruptedException {
        int threadCount = 10;
        LockNumAdd lock = new LockNumAdd(threadCount);
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executor.submit(lock);
        }
        lock.latch.await();
        int result = lock.getNum();
        executor.shutdown();
        return result;
    }

    private static int testNoLockAdd() throws InterruptedException {
        int threadCount = 10;
        NoLockNumAdd lock = new NoLockNumAdd(threadCount);
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executor.submit(lock);
        }
        lock.latch.await();
        int result = lock.getNum();
        executor.shutdown();
        return result;
    }
}
