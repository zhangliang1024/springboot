package com.zhliang.springboot.elasticsearch.service;

import com.zhliang.springboot.elasticsearch.entity.TaskRecord;
import com.zhliang.springboot.elasticsearch.mapper.TaskRecordMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 任务轨迹表(CWfTaskRecord)表服务实现类
 *
 * @author makejava
 * @since 2020-12-23 17:05:57
 */
@Service("taskRecordService")
public class TaskRecordService {
    @Resource
    private TaskRecordMapper cWfTaskRecordDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public TaskRecord queryById(String id) {
        return this.cWfTaskRecordDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    public List<TaskRecord> queryAllByLimit(int offset, int limit) {
        return this.cWfTaskRecordDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param cWfTaskRecord 实例对象
     * @return 实例对象
     */
    public TaskRecord insert(TaskRecord cWfTaskRecord) {
        this.cWfTaskRecordDao.insert(cWfTaskRecord);
        return cWfTaskRecord;
    }

    public List<TaskRecord> queryAllList() {
        return this.cWfTaskRecordDao.queryAllList();
    }
}