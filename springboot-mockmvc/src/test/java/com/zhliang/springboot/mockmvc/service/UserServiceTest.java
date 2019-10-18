package com.zhliang.springboot.mockmvc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhliang.springboot.mockmvc.SpringbootMockmvcApplicationTests;
import com.zhliang.springboot.mockmvc.entity.User;
import org.assertj.core.internal.Urls;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserServiceTest extends SpringbootMockmvcApplicationTests {


    // 注入web环境的ApplicationContext容器
    @Autowired
    protected WebApplicationContext webApplicationContext;
    /**
     * 模拟mvc测试对象
     */
    protected MockMvc mockMvc;

    /**
     * 所有测试方法执行之前执行该方法
     */
    @Before // 这个注解的作用,在每个方法之前都会执行一遍
    public void before() {
        //获取mockmvc对象实例
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void test() throws Exception{
        RequestBuilder request = null;
        request = get("/v1/user/{id}/detail", 1);
        String responseString = mockMvc.perform(request)
                .andExpect(status().isOk()) // 返回的状态是200
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn()
                .getResponse()
                .getContentAsString(); // 将数据转换为字符串
        Assert.assertNotNull(responseString);
        System.out.println(responseString);
    }


    @Test
    public void save() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        User user = new User();
        user.setId(2);
        user.setUsername("admin");
        user.setPassword("admin");
        String request = mapper.writeValueAsString(user);

        String response = mockMvc.perform(post("/v1/user/save")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(request))
                        .andDo(print())
                        .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assert.assertNotNull(response);
        System.out.println(response);
    }

    @Test
    public void testUpdateUser() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User();
        user.setId(2);
        user.setUsername("tom");
        String request = mapper.writeValueAsString(user);
        String response = mockMvc.perform(put("/v1/user/put")
                                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                                        .content(request))
                                    .andDo(print())
                                    .andExpect(status().isOk())
                                    .andReturn().getResponse().getContentAsString();
        Assert.assertNotNull(response);
        System.out.println(response);
    }

}