package com.zhliang.springboot.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * SpringBoot 自定义事件：
 *  - ApplicationContext 通过 ApplicationEvent和ApplicationListener 接口进行事件处理
 *  - 如果将实现 ApplicationListener 接口的 bean 注入到上下文中，则每次使用 ApplicationContext 发布 ApplicationEvent 时，都会通知该 bean。
 *  - 本质上，这是标准的观察者设计模式。
 *
 *
 *
 *
 *
 * 作者：吟风者
 * 链接：https://www.jianshu.com/p/812ed0680829
 * 来源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
@SpringBootApplication
public class SpringbootEventApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringbootEventApplication.class, args);
        run.publishEvent(new DemoEvent(SpringbootEventApplication .class, "随便"));
    }

}
