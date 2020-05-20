package com.zhliang.springboot.actuator.strategy.autowiredsms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;
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
@Service
public class SmsChannelFactory {

    private Map<String, SmsChannelService> serviceMap ;

    /**
     * 通过@Service注解让spring管理所有service的实现
     * 并将所有实现注入到serviceList中
     */
    @Autowired
    private List<SmsChannelService> serviceList;

    /**
     * 通过@PostConstruct注解，在SmsChannelFactory初始化后
     * 来初始化 serviceMap
     * -------------------------------------------------------------
     * 相比 new Service()的方式
     * 这种实现方式更加自动化
     *
     * ★★★
     * 经过测试发现：
     *     项目启动后 serviceMap 中已经是有值的。
     *     也就是不经过@PostContstruct初始化也是可以的
     */
    //@PostConstruct
    public void init(){
        System.out.println("SmsChannelFactory init");
        if(CollectionUtils.isEmpty(serviceList)){
            return;
        }
        serviceMap = new ConcurrentHashMap<>();
        serviceList.forEach(service -> {
            String channelType = service.getChannelType();
            //重复性校验，避免不同实现类的 getChannelType() 返回同一个值
            if(serviceMap.get(channelType) != null){
                throw new RuntimeException("同一个短信渠道只能有一个实现类");
            }
            //渠道类型为key: channelType
            //服务实现类value: service
            serviceMap.put(channelType,service);
        });
    }


    /**
     * 根据短信渠道类型返回 对应的渠道实现service
     */
    public SmsChannelService buildService(String channelType){
        return serviceMap.get(channelType);
    }

    /*-----------------------------------------------------------------------------------*/
    /**
     * 手动初始化
     * 初始化工厂，将所有的短信渠道Service放入map中
     */
    public SmsChannelFactory(){
        serviceMap = new ConcurrentHashMap<>(2);
        serviceMap.put("CHANNEL_A",new SmsChannelServiceA());
        serviceMap.put("CHANNEL_B",new SmsChannelServiceB());
        //ADD 新增一种发短信的渠道实现
        serviceMap.put("CHANNEL_C",new SmsChannelServiceC());
    }
}
