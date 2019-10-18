package com.zhliang.springboota.sync.callback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;

/**
 * @Author: colin
 * @Date: 2019/7/29 20:16
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Component
public class AsyncTask {

    @Async
    public Future<String> doTask(String task) throws Exception{
        log.info("【开始执行异步任务...】");
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            log.info("执行任务：{}",task);
            Thread.sleep(random.nextInt(1000));
        }
        log.info("【异步任务-执行完毕...】");
        return new AsyncResult<>(task);
    }


    @Async
    public void taskReturnValueMange(List<Future<String>> futures) throws Exception{
        log.info("【开始执行异步 - 获取返回结果的任务...】");
        StringBuilder sb = new StringBuilder();
        for (Future<String> future : futures) {
            sb.append(future.get());
        }
        log.info("【异步任务返回结果】 ：{}",sb.toString());
    }



}
