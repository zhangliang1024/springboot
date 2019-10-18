package com.zhliang.springboot.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: colin
 * @Date: 2019/8/23 15:50
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Service
public class CustomService {

    @Resource
    private CustomRepository repository;


    @Cacheable(value = "user",key = "#id")
    public Custom getCustom(Integer id) {
        log.info("查询数据库用户信息");
        return repository.findById(id);
    }


    /**
     * 删除一个用户
     * @param customId 用户id
     */
    @CacheEvict(value = "user", key = "#customId")
    public void deleteUser(Integer customId) {
        Custom custom = repository.findById(customId);
        repository.delete(custom);
    }
}
