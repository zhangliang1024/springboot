package com.zhliang.springboot.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @类描述：分页基础类
 * @创建人：zhiang
 * @创建时间：2020/12/23 10:27
 * @version：V1.0
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@PropertySource("classpath:config.properties")
@ConfigurationProperties(prefix = "page")
public class PaginationInfo {

    private int pageNum;

    private int pageSize;
}

