package com.zhliang.springboot.custom.starter.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: colin
 * @Date: 2019/9/25 15:42
 * @Description:
 * @Version: V1.0
 */
public class RequestUtils {

    public static HttpServletRequest getRequest(){
        ServletRequestAttributes sr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return sr.getRequest();
    }
}

