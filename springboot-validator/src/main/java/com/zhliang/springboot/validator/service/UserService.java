package com.zhliang.springboot.validator.service;
import	java.util.HashMap;
import	java.util.Map;

import com.zhliang.springboot.validator.dto.UserDTO;
import com.zhliang.springboot.validator.group.Update;
import com.zhliang.springboot.validator.pojo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.validator.service
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/24 20:22
 * @version：V1.0
 */
@Service
public class UserService {

    private static final Map<Long, User> data = new HashMap<Long, User> ();
    private Long num = 0L;

    public void updateById(UserDTO userDTO) {
        Long userId = userDTO.getUserId();
        User user = selectById(userId);
        BeanUtils.copyProperties(userDTO,user);
        data.put(userId,user);
    }

    public User selectById(Long userId) {
        return data.get(userId);
    }

    public void save(UserDTO userDTO) {
        Long userId = ++num;
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        user.setUserId(userId);
        data.put(userId,user);
    }
}
