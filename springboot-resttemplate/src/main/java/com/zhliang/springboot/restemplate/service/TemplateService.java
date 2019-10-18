package com.zhliang.springboot.restemplate.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhliang.springboot.restemplate.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: colin
 * @Date: 2019/8/29 11:35
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Service
public class TemplateService {

    @Autowired
    private RestTemplate restTemplate;;

    /**
     * 常规调用
     */
    public String testOne(String url){
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, null, String.class);
        return responseEntity.getBody();
    }

    /**
     * ---------------
     * -postForEntity-
     * ---------------
     * 传输JSON 或 JSON数组,对象多层嵌套
     */
    public String postForEntityDefault(String url,Object obj1,Object obj2){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Map<String,Object> param = new HashMap<String,Object>();
        Map<String,Object> param1 = new HashMap<String,Object>();

        param.put("key1",obj1);
        param.put("key2",obj2);

        param1.put("param",param);

        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(param1, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
        return responseEntity.getBody();
    }

    public String postForEntityPOJO(String url,Person person){
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, person, String.class);
        return responseEntity.getBody();
    }


    /**
     * 多种传输和接收参数的方式
     * --------------------
     * - 1. postForObject - post 请求返回一个Object对象
     * --------------------
     *   a. 第一个参数：请求url地址
     *   b. 第二个参数：请求实体HttpEntity .有三种构造方式：HttpEntity(请求体) | HttpEntity(请求头) | HttpEntity(请求体,请求头)
     *   c. 第三个参数；返回的结果类型 . 这里的String.class 表示返回结果是一个字符串
     *   d. 第四个参数：参数值. 这里有Map 和 可变参数两种形式（通常用不到，数据放在Json里传过去了）
     */


    public String postForObjectDefault(String url) throws JsonProcessingException {
        Person p = new Person();
        p.setId(100L);
        p.setUsername("zhangsan");

        ObjectMapper mapper = new ObjectMapper();
        String request = mapper.writeValueAsString(p);

        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        //请求类型 json
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        //请求类型 form表单
        //headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Accept",MediaType.APPLICATION_JSON.toString());
        
        //请求体
        HttpEntity<String> httpEntity = new HttpEntity<>(request, headers);
        //发起请求
        String response = restTemplate.postForObject(url, httpEntity, String.class);
        return response;
    }


    /**
     * --------------------
     * - 2. getForObject - get 请求返回一个Object对象
     * --------------------
     *   a. 第一个参数：请求url地址
     *   b. 第二个参数；返回的结果类型 . 这里的String.class 表示返回结果是一个字符串
     *   c. 第三个参数：参数值. 这里有Map 和 可变参数两种形式
     */
    public String getForObjectMap(String url){
        //用Map来封装请求参数
        Map map = new HashMap();
        map.put("1","hello");
        map.put("2","world");

        String response = restTemplate.getForObject(url + "?param1={1}&param2={2}", String.class, map);
        return response;
    }

    /**
     * 用 uriVariables 来封装请求参数
     * @param url
     * @return
     */
    public String getForObjectObjects(String url) {
        //用可变参数 来封装请求: 参数数量不限 会按照顺序 替换{1}{2}的值
        String response = restTemplate.getForObject(url + "?param1={1}&param2={2}", String.class, "hello","world");
        return response;
    }

    /**
     * 测试 返回POJO实体对象
     */
    public void getForEntityPOJO(String url,Long id){
        ResponseEntity<Person> responseEntity = restTemplate.getForEntity(url + "/{id}", Person.class, id);
        Person person = responseEntity.getBody();
        log.info(" get-for-entity response {}",person);
    }
    public void getForObjectPOJO(String url,Long id){
        Person person = restTemplate.getForObject(url + "/{id}", Person.class, id);
        log.info(" get-for-object response {}",person);
    }


    /**
     * SpringBoot 使用RestTemplate来调用其它服务时,请求header中的accept-charset会默认带很多编码,影响效率
     * https://www.cnblogs.com/youdream/p/9767729.html
     */
    public String headerAcceptCharset(String url){
        StringHttpMessageConverter converter = new StringHttpMessageConverter();
        // 设置为false就可以修改 header中的accept-charset属性
        converter.setWriteAcceptCharset(false);
        restTemplate.getMessageConverters().add(0, converter);

        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.ACCEPT_CHARSET, StandardCharsets.UTF_8.toString());

        //请求体
        String request = "hello world";
        HttpEntity httpEntity = new HttpEntity<>(request, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, headers, String.class);
        return responseEntity.getBody();
    }

    /**
     * Auth 请求头 验证
     */
    public String restTemplateHeaderAuth(String url,String auth){

        //设置请求头 验证
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer " + auth);
        headers.setContentType(MediaType.APPLICATION_JSON);

        //设置请求体
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        return responseEntity.getBody();
    }


    /**
     * RestTemplate提交表单数据的三种方法: https://blog.csdn.net/yiifaa/article/details/77939282
     * RestTemplate使用exchange方式
     * ★  可以执行 POST|GET方式请求
     *
     * ★ HttpEntity 与 uriVariables的使用
     * 在RestTemplate中，HttpEntity用于传递具体的参数值。而uriVariables用于格式化Http地址，而不是参数地址
     */
    public void defaultExchange(String url){
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        //设置表单提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //封装参数
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", "zhangsan");
        map.add("password", "abcd1234");

        //封装请求体
        HttpEntity<Object> httpEntity = new HttpEntity<>(map,headers);

        //执行请求
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);

        String result = responseEntity.getBody();

        log.info(" Exchange MultiValueMap result : {}",result);
    }


}
