package com.zhliang.springboot.nacos.test;

import java.util.concurrent.CountDownLatch;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.nacos.test
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2021/11/18 14:15
 * @version：V1.0
 */
public class Test {


    public static void main(String[] args) {
        for (int i = 0;; i++) {
            System.out.println("i = " + i);
            new Thread(new HoldThread()).start();
        }
    }

    static class HoldThread extends Thread{
        CountDownLatch cd = new CountDownLatch(1);

        public HoldThread(){
            this.setDaemon(true);
        }
        @Override
        public void run() {
            try {
                cd.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
