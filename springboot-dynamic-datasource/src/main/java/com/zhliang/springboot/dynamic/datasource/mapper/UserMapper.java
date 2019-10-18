package com.zhliang.springboot.dynamic.datasource.mapper;

import com.zhliang.springboot.dynamic.datasource.entity.User;
import org.springframework.stereotype.Repository;


/**
 * @Author: colin
 * @Date: 2019/9/25 19:21
 * @Description:
 * @Version: V1.0
 */
//@Repository
public interface UserMapper {


    User selectByPrimaryKey(Integer id);


}
