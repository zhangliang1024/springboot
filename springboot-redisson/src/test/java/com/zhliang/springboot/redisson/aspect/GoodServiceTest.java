package com.zhliang.springboot.redisson.aspect;

import com.zhliang.springboot.redisson.SpringbootRedissonApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static org.junit.Assert.*;

public class GoodServiceTest extends SpringbootRedissonApplicationTests {

    @Autowired
    private GoodService service;

    CyclicBarrier cb = new CyclicBarrier(100);
    CyclicBarrier cb1 = new CyclicBarrier(100);

    @Test
    public void multi() {

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    cb.await();
                    service.multi(10001L);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
            ).start();

            new Thread(() -> {
                try {
                    cb1.await();
                    service.multi(10002L);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
            ).start();
        }

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}