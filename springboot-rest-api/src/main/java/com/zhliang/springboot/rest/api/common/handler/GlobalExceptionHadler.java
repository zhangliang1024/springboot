package com.zhliang.springboot.rest.api.common.handler;

import com.zhliang.springboot.rest.api.common.enums.ArgumentResponseEnum;
import com.zhliang.springboot.rest.api.common.enums.CommonResponseEnum;
import com.zhliang.springboot.rest.api.common.enums.ServletResponseEnum;
import com.zhliang.springboot.rest.api.common.exception.BaseException;
import com.zhliang.springboot.rest.api.common.exception.BusinessException;
import com.zhliang.springboot.rest.api.common.i18n.I18nMessageSource;
import com.zhliang.springboot.rest.api.common.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @类描述：
 *   全局统一异常处理
 * @创建人：zhiang
 * @创建时间：2020/5/15 16:24
 * @version：V1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHadler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHadler.class);
    /**
     * 生产环境
     */
    private final static String ENV_PROD = "prod";

    /**
     * 获取当前环境
     */
    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    private I18nMessageSource i18nMessageSource;

    /**
     * 获取国际化消息
     */
    public String getI18nMessage(BaseException e) {
        String code = "response." + e.getResponseEnum().toString();
        String message = i18nMessageSource.getMessage(code, e.getArgs());
        if (message == null || message.isEmpty()) {
            return e.getMessage();
        }
        return message;
    }

    /**
     * 捕获业余异常
     */
    @ExceptionHandler(value = BusinessException.class)
    public ErrorResponse handlerBussinesException(BusinessException e){
        logger.error(e.getMessage(),e);
        return new ErrorResponse(e.getResponseEnum().getCode(),getI18nMessage(e));
    }

    /**
     * 捕获基础异常
     */
    @ExceptionHandler(value = BaseException.class)
    public ErrorResponse handlerBussinesException(BaseException e){
        logger.error(e.getMessage(),e);
        return new ErrorResponse(e.getResponseEnum().getCode(),getI18nMessage(e));
    }

    /**
     * 未到达Controller层的相关异常
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler({
            NoHandlerFoundException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpMediaTypeNotAcceptableException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            // BindException.class,
            // MethodArgumentNotValidException.class
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            MissingServletRequestPartException.class,
            AsyncRequestTimeoutException.class
    })
    public ErrorResponse handleServletException(Exception e) {
        logger.error(e.getMessage(), e);
        int code = CommonResponseEnum.SERVER_ERROR.getCode();
        try {
            ServletResponseEnum servletExceptionEnum = ServletResponseEnum.valueOf(e.getClass().getSimpleName());
            code = servletExceptionEnum.getCode();
        } catch (IllegalArgumentException e1) {
            logger.error("class [{}] not defined in enum {}", e.getClass().getName(), ServletResponseEnum.class.getName());
        }
        // 为生产环境时, 不适合把具体的异常信息展示给用户, 比如404.
        if (ENV_PROD.equals(profile)) {
            code = CommonResponseEnum.SERVER_ERROR.getCode();
            BaseException baseException = new BaseException(CommonResponseEnum.SERVER_ERROR);
            String message = getI18nMessage(baseException);
            return new ErrorResponse(code, message);
        }
        return new ErrorResponse(code, e.getMessage());
    }

    /**
     * 参数绑定异常
     */
    @ExceptionHandler(value = BindException.class)
    public ErrorResponse handleBindException(BindException e) {
        log.error("参数绑定异常：{}", e);
        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 参数校验(Valid)异常，将校验失败的所有异常组合成一条错误信息
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ErrorResponse handleValidException(MethodArgumentNotValidException e) {
        log.error("参数绑定校验异常：{}", e);
        return wrapperBindingResult(e.getBindingResult());
    }



    /**
     * 包装绑定异常结果
     */
    private ErrorResponse wrapperBindingResult(BindingResult bindingResult) {
        StringBuilder msg = new StringBuilder();
        for (ObjectError error : bindingResult.getAllErrors()) {
            msg.append(", ");
            if (error instanceof FieldError) {
                msg.append(((FieldError)error).getField()).append(": ");
            }
            msg.append(error.getDefaultMessage() == null ? "" : error.getDefaultMessage());
        }
        return new ErrorResponse(ArgumentResponseEnum.VALID_ERROR.getCode(), msg.substring(2));
    }


    /**
     * 未定义异常
     */
    @ExceptionHandler(value = Exception.class)
    public ErrorResponse handleException(Exception e) {
        log.error(e.getMessage(), e);
        // 当为生产环境, 不适合把具体的异常信息展示给用户, 比如数据库异常信息.
        if (ENV_PROD.equals(profile)) {
            int code = CommonResponseEnum.SERVER_ERROR.getCode();
            BaseException baseException = new BaseException(CommonResponseEnum.SERVER_ERROR);
            String message = getI18nMessage(baseException);
            return new ErrorResponse(code, message);
        }
        return new ErrorResponse(CommonResponseEnum.SERVER_ERROR.getCode(), e.getMessage());
    }

}
