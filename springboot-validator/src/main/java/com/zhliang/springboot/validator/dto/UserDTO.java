package com.zhliang.springboot.validator.dto;

import com.zhliang.springboot.validator.annotation.IdentityCardNumber;
import com.zhliang.springboot.validator.group.Create;
import com.zhliang.springboot.validator.group.Update;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.validator.dto
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/24 20:09
 * @version：V1.0
 */
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /*** 用户ID*/
    @NotNull(message = "用户id不能为空", groups = Update.class)
    private Long userId;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Length(max = 20, message = "用户名不能超过20个字符", groups = {Create.class, Update.class})
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9\\*]*$", message = "用户昵称限制：最多20字符，包含文字、字母和数字")
    private String username;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误", groups = {Create.class, Update.class})
    private String mobile;

    @NotBlank(message = "身份证号不能为空")
    @IdentityCardNumber(message = "身份证信息有误,请核对后提交")
    private String clientCardNo;

    /**
     * 性别
     */
    private String sex;

    /**
     * 邮箱
     */
    @NotBlank(message = "联系邮箱不能为空")
    @Email(message = "邮箱格式不对")
    private String email;

    /**
     * 密码
     */
    private String password;

    /*** 创建时间 */
    @Future(message = "时间必须是将来时间", groups = {Create.class})
    private Date createTime;

}
/*@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    *//*** 用户ID*//*
    @NotNull(message = "用户id不能为空")
    private Long userId;

    *//** 用户名*//*
    @NotBlank(message = "用户名不能为空")
    @Length(max = 20, message = "用户名不能超过20个字符")
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9\\*]*$", message = "用户昵称限制：最多20字符，包含文字、字母和数字")
    private String username;

    *//** 手机号*//*
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    private String mobile;

    *//**性别*//*
    private String sex;

    *//** 邮箱*//*
    @NotBlank(message = "联系邮箱不能为空")
    @Email(message = "邮箱格式不对")
    private String email;

    *//** 密码*//*
    private String password;

    *//*** 创建时间 *//*
    @Future(message = "时间必须是将来时间")
    private Date createTime;

}*/
