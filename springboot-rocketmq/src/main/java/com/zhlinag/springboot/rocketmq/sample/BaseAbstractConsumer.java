package com.zhlinag.springboot.rocketmq.sample;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.common.message.MessageExt;

/**
 *                     .::::.
 *                   .::::::::.
 *                  :::::::::::    佛主保佑、永无Bug
 *              ..:::::::::::'
 *            '::::::::::::'
 *              .::::::::::
 *         '::::::::::::::..
 *              ..::::::::::::.
 *            ``::::::::::::::::
 *             ::::``:::::::::'        .:::.
 *            ::::'   ':::::'       .::::::::.
 *          .::::'      ::::     .:::::::'::::.
 *         .:::'       :::::  .:::::::::' ':::::.
 *        .::'        :::::.:::::::::'      ':::::.
 *       .::'         ::::::::::::::'         ``::::.
 *   ...:::           ::::::::::::'              ``::.
 *  ```` ':.          ':::::::::'                  ::::..
 *                     '.:::::'                    ':'````..
 */
@Slf4j
public abstract class BaseAbstractConsumer<T>{
  
    protected T body;
    
    /**
     * 校验 RocketMQ Message
     */
    protected boolean isVerify(MessageExt message, Class<T> clazz) {
        String body = new String(message.getBody());
        if (StringUtils.isBlank(body)) {
            log.info("MQ消息体为空");
            return false;
        }
        log.info("MQ消费者 - 接收到的消息内容：{}", body);
        JSONValidator a = JSONValidator.from(body);
        if (a.validate()) {
            this.body = JSON.parseObject(body, clazz);
        } else {
            this.body = (T) body;
        }
        return true;
    }
 
    /**
     * MQ 消息执行公共处理部分
     */
    protected void todo() {
        // 加锁 or 其他处理
        handle();
    }
 
    /**
     * 抽象钩子算法
     */
    protected abstract void handle();
    
}