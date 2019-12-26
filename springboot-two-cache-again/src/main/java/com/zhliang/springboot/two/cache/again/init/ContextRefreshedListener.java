package com.zhliang.springboot.two.cache.again.init;

import com.zhliang.springboot.two.cache.again.aspect.MultiCacheAspect;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.two.cache.again.init
 * @类描述：用于spring加载完成后，找到项目中是否有开启缓存的注解@EnableCaching
 * @创建人：colin
 * @创建时间：2019/12/6 15:28
 * @version：V1.0
 */
@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * 使用一个spring容器加载完成的监听器:
     *  在监听器里找到是否有被@EnableCaching注解修饰的类，如果有就从spring容器拿到MultiCacheAspect对象，然后将cacheEnable设置成true
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 判断根容器为Spring容器，防止出现调用两次的情况（mvc加载也会触发一次）
        if(event.getApplicationContext().getParent()==null){
            //得到所有被@EnableCaching注解修饰的类
            Map<String,Object> beans = event.getApplicationContext().getBeansWithAnnotation(EnableCaching.class);
            if(beans != null && !beans.isEmpty()) {
                MultiCacheAspect multiCache = (MultiCacheAspect)event.getApplicationContext().getBean("multiCacheAspect");
                multiCache.setCacheEnable(true);
            }

        }
    }

}
