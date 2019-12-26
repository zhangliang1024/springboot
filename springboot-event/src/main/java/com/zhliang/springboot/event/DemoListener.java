package com.zhliang.springboot.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.event
 * @类描述：自定义监听
 * @创建人：colin
 * @创建时间：2019/12/4 13:58
 * @version：V1.0
 */
@Component
public class DemoListener implements ApplicationListener<DemoEvent> {

    @Override
    public void onApplicationEvent(DemoEvent event) {
        System.out.println("自定义事件");
        System.out.println(event);
    }

}
