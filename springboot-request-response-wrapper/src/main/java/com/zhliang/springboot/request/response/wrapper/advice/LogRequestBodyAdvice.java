package com.zhliang.springboot.request.response.wrapper.advice;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * @创建人：zhiang
 * @创建时间：2020/5/28 17:29
 * @version：V1.0
 */
@Slf4j
//此处设置需要当前Advice执行的域，默认全局生效
@ControllerAdvice(basePackages = "com.zhliang.springboot.request.response.wrapper.controller")
public class LogRequestBodyAdvice implements RequestBodyAdvice {

    /**
     * 第一个调用的方法：判断当前的拦截器(advice是否支持)
     * @param methodParameter 方法参数
     * @param type    目标类型
     * @param aClass  使用的消息转换器
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    /**
     * 如果body 体没有内容就执行这个方法 后面的不会执行了
     */
    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        Method method = methodParameter.getMethod();
        log.info("handleEmptyBody {}.{}",method.getDeclaringClass().getSimpleName(),method.getName());

        return o;
    }

    /**
     * 重点：在body 被read读 | 转换 之前进行调用
     */
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter,
                                           Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        return httpInputMessage;
    }

    /**
     * 在body体已经被转换为Object后执行
     */
    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter,
                                Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        Method method = methodParameter.getMethod();
        log.info("afterBodyRead {}.{}: {}",method.getDeclaringClass().getSimpleName(),method.getName(), JSON.toJSONString(o));
        return o;
    }

}
