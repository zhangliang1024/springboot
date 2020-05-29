package com.zhliang.springboot.request.response.wrapper.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.request.response.wrapper.interceptor
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/27 19:57
 * @version：V1.0
 */
public class WarpperHandlerInterceptor implements HandlerInterceptor {

    /**
     * HttpServletRequestWrapper处理request数据流多次读取问题:
     *    https://blog.csdn.net/kaizhangzhang/article/details/97900961
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ReadHttpServletRequestWarpper requestWarpper = new ReadHttpServletRequestWarpper(request);
        //获取业务参数
        Map<String, String[]> parameterMap = requestWarpper.getParameterMap();


        return false;
    }
}
