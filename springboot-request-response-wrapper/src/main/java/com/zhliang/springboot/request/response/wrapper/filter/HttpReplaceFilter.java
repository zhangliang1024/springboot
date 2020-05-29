package com.zhliang.springboot.request.response.wrapper.filter;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.request.response.wrapper
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/27 19:01
 * @version：V1.0
 */
@Component
@ServletComponentScan
//这里的Url根据实际情况而定
@WebFilter(urlPatterns = "/north/*", filterName = "httpFilter")
public class HttpReplaceFilter implements Filter {


    /**
     * 用HttpServletRequestWarpper来修改http请求中的json数据
     *   ：https://www.jianshu.com/p/7e55801fa5d1
     *
     * 业务需求：
     * request请求参数：
     *  {
     *      "vsersion" : "1111",
     *      "data" : {
     *          "hearbeat_time" : 1232342435
     *      }
     *  }
     *
     * 处理后请求：
     * {"hearbeat_time" : 1232342435}
     */

    private static final Set<String> ALLOW_PATHS = new HashSet<>(Arrays.asList("/serviceName"));

    @Override

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        int length = ((HttpServletRequest) request).getContextPath().length();
        String requestURI = ((HttpServletRequest) request).getRequestURI();
        String path = requestURI.substring(length);
        //如果请求是在ALLOW_PATHS中，则允许直接请求
        if(ALLOW_PATHS.contains(path)){
            chain.doFilter(request, response);
        }
        //HttpServletRequest 没有提供相关的set方法来修改body，所以需要通过修饰类来操作
        ServletRequest requestWarpper = new BodyRequestWarpper((HttpServletRequest) request);
        chain.doFilter(requestWarpper, response);
    }
}
