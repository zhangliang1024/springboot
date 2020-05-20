package com.zhliang.springboot.actuator.strategy.autowiredsms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.actuator.strategy.sms
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/20 14:06
 * @version：V1.0
 */
@Service
public class SmsSendService {

    @Autowired
    private SmsChannelFactory factory;

    @Autowired
    private SmsAutowiredChannelConfig config;

    public void send(String phoneNo,String context){
        //从配置中 读取短信通道
        String channelType = config.getChannelType();
        //从工厂渠道中 获取渠道对应的服务类型
        SmsChannelService smsChannelService = factory.buildService(channelType);
        //发送短信
        smsChannelService.send(phoneNo,context);

    }
}
