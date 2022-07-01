package com.zhliang.springboot.actuator.strategy.annotation.po;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.actuator.strategy.annotation.po
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/9/14 17:46
 * @version：V1.0
 */
public class Order {

    /**
     * 订单来源
     */
    private String source;
    /**
     * 支付方式
     */
    private String payMethod;

    //其它...


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }
}
