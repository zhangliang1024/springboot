package com.zhliang.springboot.aop.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @Author: colin
 * @Date: 2019/9/25 16:43
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Component
public class RunTest implements ApplicationRunner {

    @Autowired
    private RestTemplate restTemplate;

    public static final String url = "http://localhost:8000/submit";
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("开始执行多线程测试...");
        CountDownLatch latch = new CountDownLatch(1);
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

        for (int i = 0; i < 10; i++) {
            String userId = "userId_" + i;
            HttpEntity entity = buildRequest(userId);
            service.submit(() -> {
                try {
                    latch.await();
                    log.info("当前线程： {},时间：{}", Thread.currentThread().getName(), System.currentTimeMillis());
                    ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
                    log.info("当前线程：{},result : {}",Thread.currentThread().getName(),response.getBody());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        latch.countDown();
    }

    private HttpEntity buildRequest(String userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "yourToken");
        Map<String, Object> body = new HashMap<>();
        body.put("userId", userId);
        return new HttpEntity<>(body, headers);
    }
}
