package com.zhliang.springboot.miaosha.service;

import com.zhliang.springboot.miaosha.CacheLockInterceptor;
import com.zhliang.springboot.miaosha.RedisClient;
import com.zhliang.springboot.miaosha.SpringbootMiaoshaApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.JedisPool;

import java.lang.reflect.Proxy;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class SeckillInterfaceImplTest extends SpringbootMiaoshaApplicationTests {


    private static Long productId_1 = 1000001L;
    private static Long productId_2 = 1000002L;

    private static String HOST = "127.0.0.1";

    private RedisClient client;
    private JedisPool pool;


    @Before
    public void beforeTest(){
         pool = new JedisPool(HOST);
    }

    @Test
    public void seckill() {
        int threadCount = 1000;
        int splitPoint = 500;

        CountDownLatch endCount = new CountDownLatch(threadCount);
        CountDownLatch beginCount = new CountDownLatch(1);

        SeckillInterfaceImpl service0 = new SeckillInterfaceImpl();

        Thread[] threads = new Thread[threadCount];
        //起500个线程 秒杀第一商品
        for (int i = 0; i < splitPoint; i++) {
            threads [i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        //等待在一个信号量上挂起
                        beginCount.await();
                        //用动态代理的方式 调用seckill
                        SeckillService proxy =
                                (SeckillService)Proxy.newProxyInstance(SeckillService.class.getClassLoader(),
                            new Class[] {SeckillService.class}, new CacheLockInterceptor(service0));
                        proxy.seckill(1000001L,productId_1);
                        endCount.countDown();
                    }catch (InterruptedException  e){
                        e.printStackTrace();
                    }
                }
            });
            threads [i].start();
        }

        for (int i = 0; i < splitPoint; i++) {
            threads [i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        //等待在一个信号量上挂起
                        beginCount.await();
                        //用动态代理的方式 调用seckill
                        SeckillService proxy =
                                (SeckillService)Proxy.newProxyInstance(SeckillService.class.getClassLoader(),
                                        new Class[] {SeckillService.class}, new CacheLockInterceptor(service0));
                        proxy.seckill(1000002L,productId_2);
                        endCount.countDown();
                    }catch (InterruptedException  e){
                        e.printStackTrace();
                    }
                }
            });
            threads [i].start();
        }

        long timeMillis = System.currentTimeMillis();
        //主线程 释放开始信号量，并等待结束信号量
        beginCount.countDown();;

        try {
            //主线程 等待结束信号量
            endCount.await();
            log.info("观察秒杀结果");
            log.info("product_1 :{}",SeckillInterfaceImpl.inventory.get(productId_1));
            log.info("product_2 :{}",SeckillInterfaceImpl.inventory.get(productId_2));

            log.info("Error Count : {}",CacheLockInterceptor.ERROR_COUNT);
            log.info("Total cost : {}",(System.currentTimeMillis() - timeMillis));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}