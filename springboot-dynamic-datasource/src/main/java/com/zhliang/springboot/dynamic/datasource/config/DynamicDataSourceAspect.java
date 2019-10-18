package com.zhliang.springboot.dynamic.datasource.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author: colin
 * @Date: 2019/9/25 19:42
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Aspect
@Component
public class DynamicDataSourceAspect {

    /**
     * 定义切点: service包 以及其子包下 第一个参数为String 的方法作为切点
     */
    @Pointcut("execution( * com.zhliang.springboot.dynamic.datasource.service..*.*(String, ..))")
    public void serviceAspect() {}

    /**
     * 选择数据源
     */
    @Before("serviceAspect()")
    public void switchDataSource(JoinPoint point) {
        Object[] params = point.getArgs();
        String param = (String) params[0];

        if(StringUtils.isBlank(param)) return;

        if(DynamicRoutingDataSource.isExistDataSource(param) && !param.equals(DynamicDataSourceContextHolder.getDataSourceKey()) ) {
            DynamicDataSourceContextHolder.setDataSourceKey(param);
        }

        log.info("Switch DataSource to [{}] in Method [{}]",DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
    }

    /**
     * 重置数据源
     */
    @After("serviceAspect()")
    public void restoreDataSource(JoinPoint point) {
        DynamicDataSourceContextHolder.clearDataSourceKey();
        log.info("Restore DataSource to [{}] in Method [{}]",DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
    }
}
