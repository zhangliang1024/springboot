package com.zhliang.springboot.rest.api.advice;

import com.zhliang.springboot.rest.api.annotation.ResponseResult;
import com.zhliang.springboot.rest.api.constant.CommonConstant;
import com.zhliang.springboot.rest.api.response.ErrorResult;
import com.zhliang.springboot.rest.api.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.rest.api.advice
 * @类描述：
 * @创建人：colin
 * @创建时间：2020/1/6 10:38
 * @version：V1.0
 */
@Slf4j
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

    /**
     * 是否请求包含了@ResponseResult包装注解,没有就直接返回，不需要重写返回体
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        //判断请求,是否有包装标记
        ResponseResult responseResult = (ResponseResult) request.getAttribute(CommonConstant.RESPONSE_RESULT_ANN);
        return responseResult == null ? false : true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        log.info("进入到返回体【格式重写中】");
        if(body instanceof ErrorResult){
            ErrorResult errorResult = (ErrorResult) body;
            return Result.failure(errorResult.getCode(),errorResult.getMessage());
        }

        return Result.success(body);
    }
}
