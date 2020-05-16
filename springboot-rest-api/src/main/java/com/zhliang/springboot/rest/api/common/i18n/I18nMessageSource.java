package com.zhliang.springboot.rest.api.common.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * @类描述：
 *  信息国际化处理
 * @创建人：zhiang
 * @创建时间：2020/5/16 10:08
 * @version：V1.0
 */
@Service
public class I18nMessageSource {

    @Autowired
    private MessageSource messageSource;

    /**
     * 获取国际化消息
     * 解析code对应的信息进行返回，如果对应code不能被解析，则返回默认信息defaultMessage
     * @param code 需要进行解析的code,对应资源文件中的一个属性名
     * @param args 需要用来替换code对应的信息中包含参数的内容：如 {0},{1,data},{2,time}
     * @param defaultMessage 当对应code信息不存在时则需要返回默认值
     * @return
     */
    public String getMessage(String code,Object[] args,String defaultMessage){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code,args,defaultMessage,locale);
    }

    /**
     * 解析code对应的信息进行返回，如果对应的code不能被解析则返回默认信息 ""。
     */
    public String getMessage(String code,Object[] args){
        return getMessage(code, args, "");
    }

    /**
     * 获取国际化消息
     * @param code 需要进行解析的code，对应资源文件中的一个属性名
     */
    public String getMessage(String code){
        return getMessage(code,null);
    }

}
