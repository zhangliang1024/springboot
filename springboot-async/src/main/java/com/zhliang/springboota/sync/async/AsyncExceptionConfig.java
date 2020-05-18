package com.zhliang.springboota.sync.async;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.lang.reflect.Method;

/**
 * @类描述：@Async 异步异常处理
 * @创建人：zhiang
 * @创建时间：2020/5/9 10:07
 * @version：V1.0
 *Spring Boot @Async 异步异常处理：
 * 异步处理的方式：
 * 1.对于无返回值的异步任务，配置AsyncExceptionConfig类，统一处理
 * 2.对于有返回值的异步任务，可以在contoller层捕获异常，进行处理
 *   或者
 *   通过AsyncResult捕获异常
 */
@Configuration
public class AsyncExceptionConfig implements AsyncConfigurer {

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SpringAsyncExceptionHandler();
    }

    class SpringAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
            System.out.println("------我是Async无返回方法的异常处理方法---------");
        }
    }
}
