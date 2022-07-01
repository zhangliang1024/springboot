package com.zhliang.springboot.apollo.config;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.stereotype.Component;


/**
 * @类描述: 配置刷新监听
 * @创建人：zhiang
 * @创建时间：2021/1/2 18:35
 * @version：V1.0
 */
@Component
public class RefreshConfig {

    @Autowired
    private RefreshScope refreshScope;

    @ApolloConfigChangeListener({"application"})
    public void onChange(ConfigChangeEvent event){
        boolean refresh = false;
        for (String key : event.changedKeys()) {
            if(key.startsWith("people")){
                refresh = true;
                break;
            }
        }
        if(!refresh){
            return;
        }
        refreshScope.refresh("peopleConfig");
    }
}
