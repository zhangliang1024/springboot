package com.zhliang.springboot.resttemplate.ssl.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * @Author: colin
 * @Date: 2019/10/14 14:15
 * @Description:
 * @Version: V1.0
 */
@Slf4j
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        log.error("TAG=AsyncException, msg={}, method={}, params={}", ex.getMessage(), method.getName(), params);
    }
}
