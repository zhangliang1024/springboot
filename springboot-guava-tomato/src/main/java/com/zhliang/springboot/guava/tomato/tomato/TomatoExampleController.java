package com.zhliang.springboot.guava.tomato.tomato;

import com.github.tomato.annotation.Repeat;
import com.github.tomato.annotation.TomatoToken;
import com.github.tomato.core.RepeatTypeEnum;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.guava.tomato.tomato
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/12 14:33
 * @version：V1.0
 */
@RequestMapping("/api")
@RestController
public class TomatoExampleController {

    //指定拦截策略：固定窗口策略
    @Repeat(scope = 300,message = "重复提交，请稍后重试",type = RepeatTypeEnum.FIXED_WINDOW)
    @GetMapping("/tt")
    public String getUser(@TomatoToken String name) {
        System.out.println(System.currentTimeMillis() + ":" + name);
        String s = System.currentTimeMillis() + ":" + name;
        return s;
    }

    //默认控制范围300ms,从对象类型入参中获取唯一键值
    @Repeat
    @PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String postUserName(@TomatoToken("userName") @RequestBody UserRequest userRequest) {
        System.out.println(System.currentTimeMillis() + ":" + userRequest.getUserName());
        String s = System.currentTimeMillis() + ":" + userRequest.getUserName();
        return s;
    }

    //默认控制范围300ms，指定异常类型
    @Repeat(throwable = NullPointerException.class, message = "禁止重复提交")
    @PostMapping(value = "/form")
    public String postUserName(@TomatoToken("userName") HttpServletRequest userRequest) {
        System.out.println(System.currentTimeMillis() + ":" + userRequest.getParameter("userName"));
        String s = System.currentTimeMillis() + ":" + userRequest.getParameter("userName");
        return s;
    }
}
