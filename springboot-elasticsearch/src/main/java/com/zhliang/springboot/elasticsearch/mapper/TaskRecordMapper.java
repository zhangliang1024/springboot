package com.zhliang.springboot.elasticsearch.mapper;

import com.zhliang.springboot.elasticsearch.entity.TaskRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 任务轨迹表(TaskRecord)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-23 17:05:56
 */
public interface TaskRecordMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TaskRecord queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TaskRecord> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param cWfTaskRecord 实例对象
     * @return 对象列表
     */
    List<TaskRecord> queryAll(TaskRecord cWfTaskRecord);

    /**
     * 新增数据
     *
     * @param cWfTaskRecord 实例对象
     * @return 影响行数
     */
    int insert(TaskRecord cWfTaskRecord);

    /**
     * 修改数据
     *
     * @param cWfTaskRecord 实例对象
     * @return 影响行数
     */
    int update(TaskRecord cWfTaskRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);


    List<TaskRecord> queryAllList();
}