package com.zhliang.springboot.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.event
 * @类描述：消息发布者
 * @创建人：colin
 * @创建时间：2019/12/4 13:59
 * @version：V1.0
 */
@Component
public class DemoPublisher {

    private final ApplicationContext applicationContext;

    @Autowired
    public DemoPublisher(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void publish(String message) {
        applicationContext.publishEvent(new DemoEvent(this, message));
    }


}
