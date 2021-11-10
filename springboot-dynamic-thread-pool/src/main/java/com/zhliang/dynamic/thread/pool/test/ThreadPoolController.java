package com.zhliang.dynamic.thread.pool.test;

import com.eip.cloud.dynamic.thread.pool.core.DynamicThreadPoolExecutor;
import com.eip.cloud.dynamic.thread.pool.core.DynamicThreadPoolManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @类描述：测试线程池
 * @创建人：zhiang
 * @创建时间：2021/11/9 10:22
 */
@Slf4j
@RestController
public class ThreadPoolController {

    @Autowired
    private DynamicThreadPoolManager dynamicThreadPoolManager;

    @GetMapping("/execute/{threadName}")
    public String doExecute1(@PathVariable("threadName") String threadName){
        DynamicThreadPoolExecutor threadPoolExecutor = dynamicThreadPoolManager.getThreadPoolExecutor(threadName);

        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(()->{
                log.info(" === >>>  thread name : {}",Thread.currentThread().getName());
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        return "success";
    }
}
