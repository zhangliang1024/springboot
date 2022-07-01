package com.zhliang.springboot.elasticsearch.mapper;

import com.zhliang.springboot.elasticsearch.entity.Location;
import com.zhliang.springboot.elasticsearch.mapper.BaseMapper;

import java.util.List;

/**
 * @Author: zhliang
 * @Date: 2020/12/23 10:31
 * @Description:
 * @Version: V1.0
 */
public interface LocationMapper extends BaseMapper<Location,Long> {

    int deleteByPrimaryKey(Long id);

    Long insert(Location record);

    Long insertSelective(Location record);

    Location selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Location record);

    int updateByPrimaryKey(Location record);

    List<Location> getList(Location location);

    List<Location> getList2(Location location);

    int insertBatchByOn(List<Location> locations);

    int getCount(Location location);
}
