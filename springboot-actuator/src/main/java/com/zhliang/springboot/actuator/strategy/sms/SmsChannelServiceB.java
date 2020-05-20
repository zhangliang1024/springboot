package com.zhliang.springboot.actuator.strategy.sms;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.actuator.strategy.sms
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/20 13:58
 * @version：V1.0
 */
public class SmsChannelServiceB implements SmsChannelService {

    @Override
    public void send(String phoneNo, String content) {
        System.out.println("SmsChannelServiceB send sms ...");
    }
}
