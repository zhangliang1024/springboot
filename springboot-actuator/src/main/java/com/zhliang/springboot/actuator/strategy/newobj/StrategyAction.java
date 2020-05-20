package com.zhliang.springboot.actuator.strategy.newobj;

/**
 * @创建人：zhiang
 * @创建时间：2020/5/19 20:21
 * @version：V1.0
 *
 *  策略模式
 */
public class StrategyAction {

    public static void main(String[] args) {
        Context context = new Context(new BStrategy());
        context.onMethod();
    }
}
