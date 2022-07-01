package com.zhliang.springboot.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @类描述：数据存储对象
 * @创建人：zhiang
 * @创建时间：2020/12/23 09:27
 * @version：V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElasticEntity<T> {

    /**
     * 主键标识，用户ES持久化
     */
    private String id;

    /**
     * JSON对象，实际存储数据
     */
    private Map data;
}
