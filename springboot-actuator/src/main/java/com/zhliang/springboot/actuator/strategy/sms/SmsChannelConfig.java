package com.zhliang.springboot.actuator.strategy.sms;

import org.springframework.context.annotation.Configuration;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.actuator.strategy.sms
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/20 14:08
 * @version：V1.0
 */
@Configuration
public class SmsChannelConfig {

    private String channelType;

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }
}
