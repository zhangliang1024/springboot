package com.zhliang.springboot.dynamic.datasource.config.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: colin
 * @Date: 2019/9/25 19:35
 * @Description:
 * @Version: V1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "datasource.db1")
public class Db1Properties {

    private String driverClassName;

    private String url;

    private String username;

    private String password;

    public Map<String, Object> getProperties() {
        Map<String, Object> map = new HashMap<>();
        map.put("driverClassName", this.getDriverClassName());
        map.put("url", this.getUrl());
        map.put("username", this.getUsername());
        map.put("password", this.getPassword());
        return map;
    }
}
