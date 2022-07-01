package com.zhliang.springboot.elasticsearch.mapper;

import com.zhliang.springboot.elasticsearch.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @类描述：通用Mapper
 * @创建人：zhiang
 * @创建时间：2020/12/23 10:29
 * @version：V1.0
 */
public interface BaseMapper<T extends BaseEntity,ID extends Serializable> {

    /**
     * @author WCNGS@QQ.COM
     * @See
     * @date 2019/7/23 19:27
     * @param t
     * @return
     * @throws
     * @since
     */
    List<T> getList(T t);

    List<T> getListByCondition(Object condition);

    List<T> getListByExample(Object example);

    int deleteByPrimaryKey(ID id);

    Long insert(T t);

    Long insertSelective(T t);

    T selectByPrimaryKey(ID id);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKeyWithBLOBs(T t);

    int updateByPrimaryKey(T t);

}
