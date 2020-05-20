package com.zhliang.springboot.actuator.strategy.autowiredsms;

/**
 * @Author: zhliang
 * @Date: 2020/5/20 13:57
 * @Description:
 * @Version: V1.0
 */
public interface SmsChannelService {

    //发送短信
    void send(String phoneNo, String content);

    //关键：增加getChannelType()方法，子类实现这个方法用于标识出渠道类型
    String getChannelType();
}
