package com.zhliang.springboota.sync.controller;

import com.zhliang.springboota.sync.service.AsyncService;
import com.zhliang.springboota.sync.service.FallBackAsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.Future;

/**
 * @Author: colin
 * @Date: 2019/7/26 18:20
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@RestController
@ResponseBody
public class AsyncController {


    @Autowired
    private AsyncService service;
    @Autowired
    private FallBackAsyncService fallBackAsyncService;

    @GetMapping("/async")
    public String asyncGoging() throws Exception{
        long startTime = System.currentTimeMillis();
        service.doSomething();
        String result = String.format("任务执行成功，耗时{%s}", System.currentTimeMillis() - startTime);
        return result;
    }


    /**
     * 1. 批量获取异步返回的结果，否则不能达到异步的效果
     * 2. 异步方法和调用类不要在同一个类中
     * 3. 注解扫描时，要注意过滤，避免重复实例化，因为存在覆盖的问题；
     *    @Async 就会失效
     *
     */
    @GetMapping("/async/return")
    public Map<String,Object> asyncReturn() throws Exception{
        long startTime = System.currentTimeMillis();
        log.info("【定时任务开始】：{}",startTime);
        Map<String,Object> map = new HashMap<>();
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            Future<String> future = fallBackAsyncService.doSomething(i);
            futures.add(future);
        }
        log.info("【定时任务 - 获取结果】");
        //批量获取结果
        List<String> response = new ArrayList<>();
        for (Future future : futures) {
            String string = (String) future.get();
            response.add(string);
        }
        log.info("【定时任务 - 获取结果 - 结束】");
        map.put("data", response);
        map.put("消耗时间", String.format("任务执行成功,耗时{%s}毫秒", System.currentTimeMillis() - startTime));
        log.info("【定时任务结束 - 返回结果】");
        return map;
    }


}
