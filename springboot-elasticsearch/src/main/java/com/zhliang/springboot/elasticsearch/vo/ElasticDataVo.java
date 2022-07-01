package com.zhliang.springboot.elasticsearch.vo;

import com.zhliang.springboot.elasticsearch.entity.ElasticEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @类描述：http交互Vo对象
 * @创建人：zhiang
 * @创建时间：2020/12/23 09:34
 * @version：V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElasticDataVo<T> {

    /**
     * 索引名
     */
    private String idxName;
    /**
     * 数据存储对象
     */
    private ElasticEntity elasticEntity;

}
