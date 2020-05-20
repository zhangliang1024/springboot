package com.zhliang.springboot.actuator.strategy.newobj;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.actuator.strategy
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/19 20:21
 * @version：V1.0
 */
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public void onMethod(){
        strategy.method();
    }

}
