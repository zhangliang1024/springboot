package com.zhliang.springboot.rest.api.advice;

import com.zhliang.springboot.rest.api.annotation.ResponseResult;
import com.zhliang.springboot.rest.api.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.rest.api.interceptor
 * @类描述：请求拦截器
 * @创建人：colin
 * @创建时间：2020/1/6 10:25
 * @version：V1.0
 *
 * 拦截请求，判断此请求返回的值是否需要包装。实际就是运行的时候，解析@ResponseResult注解
 * 核心思想：获取此请求，是否需要返回值包装，设置一个属性标记
 */
@Slf4j
@Component
public class ResponseResultInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 请求的方法
        if(handler instanceof HandlerMethod){
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            //判断是否在类对象上面加了注解
            if(clazz.isAnnotationPresent(ResponseResult.class)){
                //设置此请求返回体，需要包装，往下传递，在ResponseBodyAdvice 接口进行判断
                request.setAttribute(CommonConstant.RESPONSE_RESULT_ANN,clazz.getAnnotation(ResponseResult.class));

                //方法体上是否有注解
            } else if (method.isAnnotationPresent(ResponseResult.class)){
                //设置此请求返回体，需要包装，往下传递，在ResponseBodyAdvice 接口进行判断
                request.setAttribute(CommonConstant.RESPONSE_RESULT_ANN,method.getAnnotation(ResponseResult.class));
            }
        }
        return true;
    }
}
