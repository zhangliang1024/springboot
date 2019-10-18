package com.zhliang.springboot.lock.handler;

import cn.gjing.lock.AbstractLockTimeoutHandler;
import org.springframework.stereotype.Component;

/**
 * @Author: colin
 * @Date: 2019/8/23 15:35
 * @Description:  在获取锁时往往会出现长时间未获取锁,达到超时时间后会抛出异常,这里可以自定义超时异常逻辑
 * @Version: V1.0
 */
@Component
public class TimeoutHandler extends AbstractLockTimeoutHandler {

    @Override
    public void getLockTimeoutFallback(String s, int i, int i1, int i2) {

    }
}
