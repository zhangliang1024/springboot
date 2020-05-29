package com.zhliang.springboot.request.response.wrapper.advice;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.request.response.wrapper.advice
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/28 19:53
 * @version：V1.0
 */

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @title 全局请求参数处理类
 *
 *   SpringBoot使用RequestBodyAdvice进行统一参数处理:
 *      https://blog.csdn.net/xingbaozhen1210/article/details/98189562
 *
 *
 * @author Xingbz
 * @createDate 2019-8-2
 */
@ControllerAdvice(basePackages = "com.xbz.controller")//此处设置需要当前Advice执行的域 , 省略默认全局生效
public class GlobalRequestBodyAdvice implements RequestBodyAdvice {


    /** 此处如果返回false , 则不执行当前Advice的业务 */
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        //return methodParameter.getMethod().isAnnotationPresent(XXApiReq.class);
        return false;
    }

    /**
     * @title 读取参数前执行
     * @description 在此做些编码 / 解密 / 封装参数为对象的操作
     *
     *  */
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return new XHttpInputMessage(inputMessage, "UTF-8");
    }

    /**
     * @title 读取参数后执行
     * @author Xingbz
     */
    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return inputMessage;
    }

    /**
     * @title 无请求时的处理
     */
    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

}
