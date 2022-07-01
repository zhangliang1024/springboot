package com.zhliang.springboot.elasticsearch.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @类描述：查询Vo对象
 * @创建人：zhiang
 * @创建时间：2020/12/23 09:35
 * @version：V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryVo {

    /**
     * 索引名
     */
    private String idxName;
    /**
     * 需要反射的实体类型，用于对查询结果的封装
     */
    private String className;
    /**
     * 具体条件
     */
    private Map<String, Map<String,Object>> query;
}
