package com.zhliang.springboot.actuator.strategy.sms;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.actuator.strategy.sms
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/20 13:59
 * @version：V1.0
 */
public class SmsChannelFactory {

    private Map<String,SmsChannelService> serviceMap ;

    /**
     * 初始化工厂，将所有的短信渠道Service放入map中
     */
    public SmsChannelFactory(){
        serviceMap = new ConcurrentHashMap<>(2);
        serviceMap.put("CHANNEL_A",new SmsChannelServiceA());
        serviceMap.put("CHANNEL_B",new SmsChannelServiceB());
        //ADD 新增一种发短信的渠道实现
        serviceMap.put("CHANNEL_C",new SmsChannelServiceC());
    }


    /**
     * 根据短信渠道类型返回 对应的渠道实现service
     */
    public SmsChannelService buildService(String channelType){
        return serviceMap.get(channelType);
    }

}
