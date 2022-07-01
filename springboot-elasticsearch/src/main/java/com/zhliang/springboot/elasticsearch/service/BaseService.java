package com.zhliang.springboot.elasticsearch.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhliang.springboot.elasticsearch.entity.BaseEntity;
import com.zhliang.springboot.elasticsearch.entity.PaginationInfo;
import com.zhliang.springboot.elasticsearch.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/12/23 10:28
 * @version：V1.0
 */
@Transactional(readOnly = true)
public abstract class BaseService<T extends BaseEntity,ID extends Serializable> implements IBaseService<T,ID> {

    protected abstract BaseMapper<T,ID> getMapper();

    @Autowired
    private RedisUidService redisUidService;

    @Override
    public PageInfo<T> selectPage(PaginationInfo pgInfo, T t) {
        PageHelper.startPage(pgInfo.getPageNum(), pgInfo.getPageSize());
        List<T> lt = getMapper().getList(t);
        PageInfo<T> pageInfo = new PageInfo<T>(lt);
        return pageInfo;
    }

    @Override
    public PageInfo<T> selectPageByCondition(PaginationInfo pgInfo, Object condition) {
        PageHelper.startPage(pgInfo.getPageNum(), pgInfo.getPageSize());
        List<T> lt = getMapper().getListByCondition(condition);
        PageInfo<T> pageInfo = new PageInfo<T>(lt);
        return pageInfo;
    }

    @Override
    public PageInfo<T> selectByExample(PaginationInfo pgInfo, Object example) {
        PageHelper.startPage(pgInfo.getPageNum(), pgInfo.getPageSize());
        List<T> lt = getMapper().getListByExample(example);
        PageInfo<T> pageInfo = new PageInfo<T>(lt);
        return pageInfo;
    }

    @Override
    public int deleteByPrimaryKey(ID id) {
        return getMapper().deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = false)
    @Override
    public Long insert(T t) {
        Long id = getPrimaryKey(t);
        t.setId(id);
        getMapper().insert(t);
        return id;
    }

    public Long getPrimaryKey(T t){
        return Long.valueOf(redisUidService.generate(t.getClass().getSimpleName().toUpperCase()));
    }

    @Transactional(readOnly = false)
    @Override
    public Long insertSelective(T t) {
        Long id = getPrimaryKey(t);
        t.setId(id);
        getMapper().insertSelective(t);
        return id;
    }

    @Override
    public T selectByPrimaryKey(ID id) {
        return getMapper().selectByPrimaryKey(id);
    }

    @Transactional(readOnly = false)
    @Override
    public int updateByPrimaryKeySelective(T t) {
        return getMapper().updateByPrimaryKeySelective(t);
    }

    @Transactional(readOnly = false)
    @Override
    public int updateByPrimaryKeyWithBLOBs(T t) {
        return getMapper().updateByPrimaryKeyWithBLOBs(t);
    }


    /** Update entity based on primary key
     * @author WCNGS@QQ.COM
     * @See
     * @date 2019/7/23 19:31
     * @param t entity
     * @return int
     * @throws
     * @since
     */
    @Transactional(readOnly = false)
    @Override
    public int updateByPrimaryKey(T t) {
        return getMapper().updateByPrimaryKey(t);
    }

}
