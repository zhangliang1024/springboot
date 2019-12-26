package com.zhliang.springboot.validator.controller;

import com.zhliang.springboot.validator.dto.UserDTO;
import com.zhliang.springboot.validator.group.Update;
import com.zhliang.springboot.validator.pojo.User;
import com.zhliang.springboot.validator.response.RspDTO;
import com.zhliang.springboot.validator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.validator.controller
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/24 20:21
 * @version：V1.0
 */

@Validated
@RestController
@RequestMapping("user/")
public class HelloController {


    @Autowired
    private UserService userService;

    /**
     * 走参数校验注解
     *
     * @param userDTO
     * @return
     */
    @PostMapping("/save/valid")
    public RspDTO save(@RequestBody @Validated UserDTO userDTO) {
        userService.save(userDTO);
        return RspDTO.success();
    }

    /**
     * 走参数校验注解的 groups 组合校验
     *
     * @param userDTO
     * @return
     */
    @PostMapping("/update/groups")
    public RspDTO update(@RequestBody @Validated(Update.class) UserDTO userDTO) {
        userService.updateById(userDTO);
        return RspDTO.success();
    }


    /**
     * Restful风格用法 : 多个参数校验,或者@RequestParam 形式时候,需要在controller上加注@Validated
     * @param userId
     * @return
     */
    @GetMapping("/get")
    public RspDTO getUser(@RequestParam("userId") @NotNull(message = "用户id不能为空") Long userId) {
        User user = userService.selectById(userId);
        if (user == null) {
            return new RspDTO<User>().nonAbsent("用户不存在");
        }
        return new RspDTO<User>().success(user);
    }
}
