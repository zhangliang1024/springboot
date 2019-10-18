package com.zhliang.springboota.sync.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.Future;

/**
 * @Author: colin
 * @Date: 2019/7/29 18:27
 * @Description: 有返回值的异步任务
 * @Version: V1.0
 */
@Slf4j
@Service
public class FallBackAsyncService {

    @Async
    public Future<String> doSomething(int num) throws Exception{
        log.info("【有返回值的异步方法是否执行】");
        Thread.sleep(3000);
        log.info("【有返回值的异步方法确实执行了！】");
        log.info("【有返回值的异步方法执行结束】：" + new Date());
        return new AsyncResult<>(String.format("这是有返回值的定时任务 - {%s}",num));
    }
}
