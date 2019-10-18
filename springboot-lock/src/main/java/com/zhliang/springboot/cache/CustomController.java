package com.zhliang.springboot.cache;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @Author: colin
 * @Date: 2019/8/23 18:57
 * @Description:
 * @Version: V1.0
 */
@RestController
@Api("customController")
public class CustomController {


    @Autowired
    private CustomService service;

    @GetMapping("/user/{custom-id}")
    @ApiOperation(value = "查询用户",httpMethod = "GET")
    public ResponseEntity getUser(@PathVariable("custom-id") Integer id){
        return ResponseEntity.ok(service.getCustom(id));
    }

    @DeleteMapping("/user")
    @ApiOperation(value = "删除用户", httpMethod = "DELETE")
    @ApiImplicitParam(name = "customId", value = "用户Id", dataType = "int", required = true, paramType = "Query")
    @NotNull
    public ResponseEntity deleteUser(Integer customId) {
        service.deleteUser(customId);
        return ResponseEntity.ok("Successfully delete");
    }
}
