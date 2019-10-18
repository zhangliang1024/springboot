package com.zhliang.springboot.runner.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Author: colin
 * @Date: 2019/10/11 19:17
 * @Description:
 * @Version: V1.0
 */
@Component
public class ApplicationListenerImpl2 implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println(" ApplicationContext初始化或刷新完成后触发的事件..");
    }
}
