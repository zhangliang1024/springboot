package com.zhliang.springboot.actuator.strategy.sms;

/**
 * @Author: zhliang
 * @Date: 2020/5/20 13:57
 * @Description:
 * @Version: V1.0
 */
public interface SmsChannelService {

    //发送短信
    void send(String phoneNo,String content);

}
