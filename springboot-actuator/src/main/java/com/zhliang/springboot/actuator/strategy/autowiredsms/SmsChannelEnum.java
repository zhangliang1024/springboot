package com.zhliang.springboot.actuator.strategy.autowiredsms;

/**
 * @Author: zhliang
 * @Date: 2020/5/20 14:52
 * @Description:
 * @Version: V1.0
 */
public enum SmsChannelEnum {
    CHANNEL_A("CHANNEL_A"),
    CHANNEL_B("CHANNEL_B"),
    CHANNEL_C("CHANNEL_C"),
    ;

    private String channelType;

    SmsChannelEnum(String channelType) {
        this.channelType = channelType;
    }

    public String getChannelType() {
        return channelType;
    }
}
