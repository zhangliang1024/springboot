package com.zhliang.springboot.elasticsearch.service;

import com.github.pagehelper.PageInfo;
import com.zhliang.springboot.elasticsearch.entity.BaseEntity;
import com.zhliang.springboot.elasticsearch.entity.PaginationInfo;

import java.io.Serializable;

/**
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/12/23 10:26
 * @version：V1.0
 */
public interface IBaseService<T extends BaseEntity, ID extends Serializable> {

    PageInfo<T> selectPage(PaginationInfo pgInfo, T t);

    PageInfo<T> selectPageByCondition(PaginationInfo pgInfo, Object condition);

    PageInfo<T> selectByExample(PaginationInfo pgInfo, Object example);

    int deleteByPrimaryKey(ID id);

    Long insert(T t);

    Long insertSelective(T t);

    T selectByPrimaryKey(ID id);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKeyWithBLOBs(T t);

    int updateByPrimaryKey(T t);

}
