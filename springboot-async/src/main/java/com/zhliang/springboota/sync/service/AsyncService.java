package com.zhliang.springboota.sync.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

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

}
