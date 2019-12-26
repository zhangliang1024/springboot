package com.zhliang.springboot.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.event
 * @类描述：自定义事件
 * @创建人：colin
 * @创建时间：2019/12/4 13:57
 * @version：V1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DemoEvent extends ApplicationEvent {

    private String message;

    public DemoEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

}
