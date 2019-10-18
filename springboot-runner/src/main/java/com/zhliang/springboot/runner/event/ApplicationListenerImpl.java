package com.zhliang.springboot.runner.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author: colin
 * @Date: 2019/10/11 19:11
 * @Description: 事件监听者
 * @Version: V1.0
 */
@Component
public class ApplicationListenerImpl implements ApplicationListener<EventImpl> {

    @Override
    public void onApplicationEvent(EventImpl event) {
        event.getMessage();
    }
}
