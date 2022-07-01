package com.zhliang.springboot.apollo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @类描述：对象配置自动刷新(一次性获取，有变更不会自动刷新)
 * @创建人：zhiang
 * @创建时间：2021/1/2 18:32
 * @version：V1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "people")
@RefreshScope //自动刷新配置
public class PeopleConfig {

    private String username;

}
