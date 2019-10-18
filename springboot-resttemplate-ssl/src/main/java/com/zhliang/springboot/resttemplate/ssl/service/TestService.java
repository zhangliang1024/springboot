package com.zhliang.springboot.resttemplate.ssl.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: colin
 * @Date: 2019/10/14 12:08
 * @Description:
 * @Version: V1.0
 */
@Service
public class TestService {

    // 注入自定义ssl证书的restTemplate
    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    // post请求测试
    public Object testPost() {
        String url = "https://31.23.1.0:9080/command";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("deviceId", "21312112");
        jsonObject.put("expireTime", 0);
        jsonObject.put("maxRetransmit", 1);
        jsonObject.put("callbackUrl", "https://www.baidu.com");
        // 请求实体，包括请求body和请求头
        HttpEntity<JSONObject> entity = new HttpEntity<>(jsonObject, getHeaders());
        JSONObject rep = restTemplate.postForObject(url, entity, JSONObject.class);
        return rep;
    }

    /**
     * @author David Hong
     * @description 自定义请求头
     *
     * @return org.springframework.http.HttpHeaders
     */
    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer tokenString");
        return headers;
    }
}
