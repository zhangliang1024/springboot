package com.zhliang.springboot.request.response.wrapper.advice;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;

/**
 * @创建人：zhiang
 * @创建时间：2020/5/28 17:46
 * @version：V1.0
 */
@Slf4j
@ControllerAdvice
public class LogResponseBodyAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        Method method=methodParameter.getMethod();
        String url=serverHttpRequest.getURI().toASCIIString();
        log.info("beforeBodyWrite {}.{},url={},result={}",method.getDeclaringClass().getSimpleName(),method.getName(),url, JSON.toJSONString(o));

        return o;
    }
}
