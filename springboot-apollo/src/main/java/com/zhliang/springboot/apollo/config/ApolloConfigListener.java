package com.zhliang.springboot.apollo.config;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @类描述：Apollo 配置监听
 * @创建人：zhiang
 * @创建时间：2021/1/4 10:18
 * @version：V1.0
 */
@Slf4j
@Configuration
public class ApolloConfigListener implements ApplicationContextAware {
    /**
     * 日志配置常量
     */
    private static final String LOGGER_TAG = "logging.level.";

    @Resource
    private LoggingSystem loggingSystem;

    private ApplicationContext applicationContext;

    @Autowired
    private org.springframework.cloud.context.scope.refresh.RefreshScope refreshScope;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    /**
     * 配置监听
     * ApolloConfigChangeListener > value 属性默认 命名空间 "application"
     */
    @ApolloConfigChangeListener(value ={"application","common"})
    private void onChangeConfig(ConfigChangeEvent changeEvent) {
        log.info("【Apollo-config-change】>> start");

        DataSourceProperties dataSource = applicationContext.getBean(DataSourceProperties.class);


        for (String key : changeEvent.changedKeys()) {
            ConfigChange change = changeEvent.getChange(key);
            log.info("【Apollo-config-change】>> key={} , propertyName={} , oldValue={} , newValue={} ",
                    key, change.getPropertyName(), change.getOldValue(), change.getNewValue());
            if (StringUtils.containsIgnoreCase(key, LOGGER_TAG)) {
                changeLoggingLevel(key, change);
                continue;
            }
            if (StringUtils.containsIgnoreCase(key, "spring.datasource.password")) {
                dataSource.setPassword(change.getNewValue());
            }
            this.applicationContext.publishEvent(new EnvironmentChangeEvent(changeEvent.changedKeys()));
        }
        refreshScope.refresh("dataSource");
        log.info("【Apollo-config-change】>> end");
    }

    /**
     * 刷新日志级别
     */
    private void changeLoggingLevel(String key, ConfigChange change) {
        if (null == loggingSystem) {
            return;
        }
        String newLevel = change.getNewValue();
        LogLevel level = LogLevel.valueOf(newLevel.toUpperCase());
        loggingSystem.setLogLevel(key.replace(LOGGER_TAG, ""), level);
        log.info("【Apollo-logger-config-change】>> {} -> {}", key, newLevel);
    }

    /**
     * 动态刷新数据源
     */
    @RefreshScope
    @Bean
    public DataSource dataSource(DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

}
