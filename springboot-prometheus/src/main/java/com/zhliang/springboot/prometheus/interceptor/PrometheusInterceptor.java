package com.zhliang.springboot.prometheus.interceptor;

import com.zhliang.springboot.prometheus.counter.HttpConterHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @类描述：拦截器统计
 * @创建时间：2021/8/13 15:42
 * @version：V1.0
 */
public class PrometheusInterceptor implements HandlerInterceptor {

    @Autowired
    private HttpConterHelper httpConterHelper;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        httpConterHelper.count();
    }
}
