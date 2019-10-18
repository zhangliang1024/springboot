package com.zhliang.springboot.miaosha;
import java.lang.annotation.*;


/**
 * @Author: colin
 * @Date: 2019/9/5 15:54
 * @Description:
 * @Version: V1.0
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockedObject {

}
