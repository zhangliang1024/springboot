package com.zhliang.springboot.rest.api.controller;

import com.zhliang.springboot.rest.api.annotation.ResponseResult;
import com.zhliang.springboot.rest.api.entity.Order;
import com.zhliang.springboot.rest.api.exception.GlobalException;
import com.zhliang.springboot.rest.api.response.Result;
import com.zhliang.springboot.rest.api.response.ResultCode;
import com.zhliang.springboot.rest.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.rest.api.controller
 * @类描述：
 * @创建人：colin
 * @创建时间：2020/1/6 10:07
 * @version：V1.0
 */
@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    /**
     * 演示模式一
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public Result getOrder(@PathVariable("id") Integer id ){
        Order order = orderService.getOrderById(id);
        Result result = new Result(ResultCode.SUCCESS, order);
        return result;
    }

    /**
     * 演示模式二：更优雅的使用返回结构体
     * @param id
     * @return
     */
    @GetMapping("{id}/1")
    public Result getOrder_1(@PathVariable("id") Integer id ){
        if(id == 3){
            //return Result.failure(ResultCode.PARAM_IS_BLANK);
            throw new RuntimeException(ResultCode.PARAM_IS_BLANK.getMessage());
        }
        Order order = orderService.getOrderById(id);
        return Result.success(order);
    }

    /**
     * 演示模式三：通过AOP实现统一返回格式
     * @param id
     * @return
     */
    @GetMapping("{id}/2")
    @ResponseResult
    public Order getOrder_2(@PathVariable("id") @Valid @NotNull(message = "参数异常") Integer id ){
        if(id == 3){
            throw new GlobalException(ResultCode.PARAM_IS_BLANK);
        }
        Order order = orderService.getOrderById(id);
        return order;
    }
}
