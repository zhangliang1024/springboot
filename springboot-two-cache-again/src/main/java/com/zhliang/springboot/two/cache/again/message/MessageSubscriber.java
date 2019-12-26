package com.zhliang.springboot.two.cache.again.message;

import com.zhliang.springboot.two.cache.again.config.CacheFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.two.cache.again.message
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/6 14:37
 * @version：V1.0
 */
@Component
public class MessageSubscriber {

    private static final Logger logger = LoggerFactory.getLogger(MessageSubscriber.class);

    @Autowired
    private CacheFactory cacheFactory;

    /**
     * 接收到redis订阅的消息后，将ehcache的缓存失效
     * @param message 格式为name_key
     */
    public void handle(String message){

        logger.debug("redis.ehcache:"+message);
        if(StringUtils.isEmpty(message)) {
            return;
        }
        String[] strs = message.split("#");
        String name = strs[0];
        String key = null;
        if(strs.length == 2) {
            key = strs[1];
        }
        cacheFactory.ehDel(name,key);

    }

}
