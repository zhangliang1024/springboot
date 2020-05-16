package com.zhliang.springboota.sync.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.Future;

/**
 * @Author: colin
 * @Date: 2019/7/26 18:21
 * @Description: 无返回值得异步任务
 * @Version: V1.0
 */
@Slf4j
@Service
public class AsyncService {

    @Async
    public void doSomething() throws Exception{
      log.info("【异步方法是否执行】");
      Thread.sleep(3000);
      log.info("【异步方法确实执行了！】");
      log.info("【异步方法执行结束】：" + new Date());
    }

    @Async("auditExecutor")
    public void asyncTask() throws Exception {
        System.out.println("异步线程，线程名：" + Thread.currentThread().getName());
        System.out.println("异步处理方法-----start-------");
        System.out.println("------------------------在看貂蝉，不要打扰--------------");
        String noNum = "sdfdfsd";
        //把一个非数字转成数字，抛出异常来测试。
        int i = Integer.parseInt(noNum);
        Thread.sleep(2000);
        System.out.println("异步处理方法------end--------");
    }

    @Async("auditExecutor")
    public Future<String> asyncMethodWithResult() {
        // do something（可能发生异常）
        //int abc = 1/0;
        throwException();
        return new AsyncResult("hello");
    }

    public void throwException(){
        throw new RuntimeException("throw exception ...");
    }
}
