package com.zhliang.springboot.validator.pojo;

import com.zhliang.springboot.validator.group.Create;
import com.zhliang.springboot.validator.group.Update;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Future;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.validator.pojo
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/24 20:24
 * @version：V1.0
 */
@Data
public class User {

    private Long userId;
    private String username;
    private String mobile;
    private String clientCardNo;
    private String sex;
    private String email;
    private String password;
    private Date createTime;
}
