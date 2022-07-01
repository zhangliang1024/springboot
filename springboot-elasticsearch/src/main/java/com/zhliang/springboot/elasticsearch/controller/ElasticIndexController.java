package com.zhliang.springboot.elasticsearch.controller;

import com.alibaba.fastjson.JSON;
import com.zhliang.springboot.elasticsearch.response.ResponseCode;
import com.zhliang.springboot.elasticsearch.response.ResponseResult;
import com.zhliang.springboot.elasticsearch.service.BaseElasticService;
import com.zhliang.springboot.elasticsearch.vo.IdxVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @类描述：ElasticSearch索引的基本管理，提供对外查询、删除和新增功能
 * @创建人：zhiang
 * @创建时间：2020/12/23 09:32
 * @version：V1.0
 */
@Slf4j
@RequestMapping("/elastic")
@RestController
public class ElasticIndexController {

    @Autowired
    BaseElasticService baseElasticService;

    @GetMapping(value = "/")
    public ResponseResult index(String index){
        return new ResponseResult();
    }

    /**
     * @Description 创建Elastic索引
     * @param idxVo
     * @return xyz.wongs.weathertop.base.message.response.ResponseResult
     * @throws
     * @date 2019/11/19 11:07
     */
    @PostMapping(value = "/createIndex")
    public ResponseResult createIndex(@RequestBody IdxVo idxVo){
        ResponseResult response = new ResponseResult();
        try {
            //索引不存在，再创建，否则不允许创建
            if(!baseElasticService.isExistsIndex(idxVo.getIdxName())){
                String idxSql = JSON.toJSONString(idxVo.getIdxSql());
                log.warn(" idxName={}, idxSql={}",idxVo.getIdxName(),idxSql);
                baseElasticService.createIndex(idxVo.getIdxName(),idxSql);
            } else{
                response.setStatus(false);
                response.setCode(ResponseCode.DUPLICATEKEY_ERROR_CODE.getCode());
                response.setMsg("索引已经存在，不允许创建");
            }
        } catch (Exception e) {
            response.setStatus(false);
            response.setCode(ResponseCode.ERROR.getCode());
            response.setMsg(ResponseCode.ERROR.getMsg());
        }
        return response;
    }


    /**
     * @Description 判断索引是否存在；存在-TRUE，否则-FALSE
     * @param index
     * @return xyz.wongs.weathertop.base.message.response.ResponseResult
     * @throws
     * @date 2019/11/19 18:48
     */
    @GetMapping(value = "/exist/{index}")
    public ResponseResult indexExist(@PathVariable(value = "index") String index){

        ResponseResult response = new ResponseResult();
        try {
            if(!baseElasticService.isExistsIndex(index)){
                log.error("index={},不存在",index);
                response.setCode(ResponseCode.RESOURCE_NOT_EXIST.getCode());
                response.setMsg(ResponseCode.RESOURCE_NOT_EXIST.getMsg());
            } else {
                response.setMsg(" 索引已经存在, " + index);
            }
        } catch (Exception e) {
            response.setCode(ResponseCode.NETWORK_ERROR.getCode());
            response.setMsg(" 调用ElasticSearch 失败！");
            response.setStatus(false);
        }
        return response;
    }

    @GetMapping(value = "/del/{index}")
    public ResponseResult indexDel(@PathVariable(value = "index") String index){
        ResponseResult response = new ResponseResult();
        try {
            baseElasticService.deleteIndex(index);
        } catch (Exception e) {
            response.setCode(ResponseCode.NETWORK_ERROR.getCode());
            response.setMsg(" 调用ElasticSearch 失败！");
            response.setStatus(false);
        }
        return response;
    }
}
