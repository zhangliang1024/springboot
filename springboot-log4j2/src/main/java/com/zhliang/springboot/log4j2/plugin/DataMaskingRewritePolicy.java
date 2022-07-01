package com.zhliang.springboot.log4j2.plugin;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.rewrite.RewritePolicy;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.springframework.stereotype.Component;

/**
 * @类描述：修改LogEvent，定制日志内容打码
 * @创建人：zhiang
 * @创建时间：2021/3/14 14:36
 * @version：V1.0
 */
//@Component
//@Plugin(name = "DataMaskingRewritePolicy",category = "Core",elementType = "rewritePolicy",printObject = true)
public class DataMaskingRewritePolicy implements RewritePolicy {

    public static final String  OVERLAY = "**********";
    public static final int START_EXCLUED = 3;
    public static final int END_INCLUED = 16;
    public static final int BAND_ACCOUNT_LEN = 19;

    @Override
    public LogEvent rewrite(LogEvent logEvent) {
        if(!(logEvent instanceof Log4jLogEvent)){
            return logEvent;
        }
        Log4jLogEvent log4jLogEvent = (Log4jLogEvent)logEvent;

        Message message = log4jLogEvent.getMessage();

        if(!(message instanceof ParameterizedMessage)){
            return logEvent;
        }
        ParameterizedMessage parameterizedMessage = (ParameterizedMessage)message;

        //遍历日志参数
        Object[] params = parameterizedMessage.getParameters();
        if(params == null || params.length <= 0){
            return logEvent;
        }

        Object[] newParams = new Object[params.length];
        for (int i = 0; i < params.length; i++) {
            Object object = params[i];
            if(object instanceof String){
                String obj = (String)object;
                if(StringUtils.isNumeric(obj) && obj.length() == BAND_ACCOUNT_LEN){
                    newParams[i] = StringUtils.overlay(obj,OVERLAY,START_EXCLUED,END_INCLUED);
                    continue;
                }
                newParams[i] = obj;
            }
        }
        //重置message对象
        ParameterizedMessage m = new ParameterizedMessage(
                parameterizedMessage.getFormat(),
                newParams,
                parameterizedMessage.getThrowable()
        );
        Log4jLogEvent.Builder builder = log4jLogEvent.asBuilder().setMessage(m);
        return builder.build();
    }

    //插件注册
    @PluginFactory
    public static DataMaskingRewritePolicy createPolicy(){
        return new DataMaskingRewritePolicy();
    }
}
