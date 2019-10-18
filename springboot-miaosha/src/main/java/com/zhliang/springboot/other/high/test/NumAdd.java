package com.zhliang.springboot.other.high.test;

/**
 * @Author: colin
 * @Date: 2019/9/9 17:15
 * @Description:
 * @Version: V1.0
 */
public abstract class NumAdd implements Runnable {

    private int num;

    public int getNum(){
        return num;
    }

    public void addNum(){
        //稍作停顿
        sleep();
        num++;
    }

    public void resetNum(){
        num = 0;
    }

    private void sleep(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
