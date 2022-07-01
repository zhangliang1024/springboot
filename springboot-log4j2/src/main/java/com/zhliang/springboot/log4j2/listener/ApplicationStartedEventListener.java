package com.zhliang.springboot.log4j2.listener;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @类描述：Springboot使用application.yml中的属性值
 *         自定义监听器，从yml中读取属性。然后通过MDC设置到log4j2的上下文
 * @创建人：zhiang
 * @创建时间：2021/3/12 16:48
 * @version：V1.0
 */
public class ApplicationStartedEventListener implements GenericApplicationListener {

    private static final Logger logger = LoggerFactory.getLogger(AutoPrintLogListener.class);

    private static final int DEFAULT_ORDER = Ordered.HIGHEST_PRECEDENCE + 10;

    private static Class<?>[] EVENT_TYPES = {
            ApplicationStartingEvent.class,
            ApplicationEnvironmentPreparedEvent.class,
            ApplicationPreparedEvent.class,
            ContextClosedEvent.class,
            ApplicationFailedEvent.class};

    private static Class<?>[] SOURCE_TYPES = {SpringApplication.class, ApplicationContext.class};

    //注入env失败，这里注入的是null
    @Autowired
    private Environment env;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            ConfigurableEnvironment envi = ((ApplicationEnvironmentPreparedEvent) event).getEnvironment();
            MutablePropertySources mps = envi.getPropertySources();
            //这里通过configProperties获取PropertySource
            PropertySource<?> ps = mps.get("configurationProperties");

            if (ps != null && ps.containsProperty("spring.application.name")) {
                String appName = (String)ps.getProperty("spring.application.name");
                if (StringUtils.isNotBlank(appName)) {
                    MDC.put("appName", appName);
                }
            }
            String port = String.valueOf(ps.getProperty("server.port"));
            if (StringUtils.isNotBlank(port)) {
                MDC.put("port", port);
            }
            String ip = getIp();
            if (StringUtils.isNotBlank(ip)) {
                logger.info("springboot ip : {}", ip);
                MDC.put("ip", ip);
            }
        }
    }

    private String getIp() {
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }

        return ip.getHostAddress();
    }

    @Override
    public boolean supportsEventType(ResolvableType resolvableType) {
        return isAssignableFrom(resolvableType.getRawClass(), EVENT_TYPES);
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return isAssignableFrom(sourceType, SOURCE_TYPES);
    }

    private boolean isAssignableFrom(Class<?> type, Class<?>... supportedTypes) {
        if (type != null) {
            for (Class<?> supportedType : supportedTypes) {
                if (supportedType.isAssignableFrom(type)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return DEFAULT_ORDER;
    }
}
