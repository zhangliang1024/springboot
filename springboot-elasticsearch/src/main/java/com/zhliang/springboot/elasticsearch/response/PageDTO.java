package com.zhliang.springboot.elasticsearch.response;

import lombok.Data;

/**
 * @项目名称：springboot
 * @创建人：zhiang
 * @创建时间：2020/12/24 13:25
 * @version：V1.0
 */
@Data
public class PageDTO<T> {

    private Integer pageNum;
    private Integer pageSize;
    private long total;
    private T list;

}
