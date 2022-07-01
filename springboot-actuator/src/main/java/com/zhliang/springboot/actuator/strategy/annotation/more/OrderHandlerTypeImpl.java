package com.zhliang.springboot.actuator.strategy.annotation.more;

import java.lang.annotation.Annotation;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.actuator.strategy.annotation
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/9/14 19:36
 * @version：V1.0
 */
public class OrderHandlerTypeImpl implements OrderHandlerTypeI {

    private String source;
    private String payMethod;

    OrderHandlerTypeImpl(String source,String payMethod){
        this.source = source;
        this.payMethod = payMethod;
    }

    @Override
    public String source() {
        return source;
    }

    @Override
    public String payMethod() {
        return payMethod;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode += (127 * "source".hashCode()) ^ source.hashCode();
        hashCode += (127 * "payMethod".hashCode()) ^ payMethod.hashCode();
        return hashCode;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OrderHandlerTypeI)) {
            return false;
        }
        OrderHandlerTypeI other = (OrderHandlerTypeI) obj;
        return source.equals(other.source()) && payMethod.equals(other.payMethod());
    }
}
