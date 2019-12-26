package com.zhliang.springboot.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.event
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/4 14:07
 * @version：V1.0
 */
@Component
public class DemoListener2 {

    @EventListener
    public void onApplicationEvent(DemoEvent demoEvent) {
        System.out.println("收到了：" + demoEvent.getSource() + "消息;时间：" + demoEvent.getTimestamp());
    }
}
