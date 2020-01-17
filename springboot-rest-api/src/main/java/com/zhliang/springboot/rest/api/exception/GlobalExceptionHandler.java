package com.zhliang.springboot.rest.api.exception;

import com.zhliang.springboot.rest.api.response.ErrorResult;
import com.zhliang.springboot.rest.api.response.Result;
import com.zhliang.springboot.rest.api.response.ResultCode;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.rest.api.exception
 * @类描述：
 * @创建人：colin
 * @创建时间：2020/1/6 12:54
 * @version：V1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 这里处理太垃圾了 response中的返回类型也处理的不是很友好
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ErrorResult exceptionHandler(HttpServletRequest request, Exception e){
        e.printStackTrace();

            //自定义全局异常
        if(e instanceof GlobalException) {
            GlobalException exception = (GlobalException) e;
            return ErrorResult.failure(exception.getResultCode());
            //绑定异常
        }else if(e instanceof BindException) {
            BindException ex = (BindException) e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            return ErrorResult.PARAM_TYPE_BIND_ERROR.fillArgs(msg);
            //类型转换异常
        }else if(e instanceof NumberFormatException) {
            String message = e.getMessage();
            return ErrorResult.PARAM_TYPE_BIND_ERROR.fillArgs(message);
            //参数类型异常
        }else if(e instanceof MethodArgumentTypeMismatchException) {
            String message = e.getMessage();
            return ErrorResult.PARAM_TYPE_BIND_ERROR.fillArgs(message);
            //TODO 404 页面异常处理还待完善，会返回一堆乱七八糟的东西
        }else if(e instanceof NoHandlerFoundException) {
            String message = e.getMessage();
            return ErrorResult.PARAM_TYPE_BIND_ERROR.fillArgs(message);
            //系统异常
        }else{
            return ErrorResult.failure(ResultCode.SYSTEM_ERROR);
        }
    }

}
