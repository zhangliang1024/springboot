package com.zhliang.springboot.restemplate.https;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: colin
 * @Date: 2019/8/29 22:59
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Service
public class HttpsService {


    public void testHttps(String url,String param){

        RestTemplate restTemplateHttps = new RestTemplate(new HttpsClientRequestFactory());

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        messageConverters.add(stringHttpMessageConverter);
        restTemplateHttps.setMessageConverters(messageConverters);

        ResponseEntity<String> responseEntity = restTemplateHttps.postForEntity(url, param, String.class);
        if (responseEntity != null && responseEntity.getStatusCodeValue() == 200) {
            String message = responseEntity.getBody();
            log.info(" Response Msg {}",message);
        }
    }
}
