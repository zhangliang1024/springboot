package com.zhliang.springboot.elasticsearch.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhliang.springboot.elasticsearch.entity.ElasticEntity;
import com.zhliang.springboot.elasticsearch.entity.Location;
import com.zhliang.springboot.elasticsearch.entity.TaskRecord;
import com.zhliang.springboot.elasticsearch.response.ResponseCode;
import com.zhliang.springboot.elasticsearch.response.ResponseResult;
import com.zhliang.springboot.elasticsearch.service.BaseElasticService;
import com.zhliang.springboot.elasticsearch.service.LocationService;
import com.zhliang.springboot.elasticsearch.service.TaskRecordService;
import com.zhliang.springboot.elasticsearch.utils.ElasticUtil;
import com.zhliang.springboot.elasticsearch.utils.ObjToMap;
import com.zhliang.springboot.elasticsearch.utils.StringUtils;
import com.zhliang.springboot.elasticsearch.vo.ElasticDataVo;
import com.zhliang.springboot.elasticsearch.vo.QueryVo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @类描述：数据管理
 * @创建人：zhiang
 * @创建时间：2020/12/23 09:38
 */
@Slf4j
@RequestMapping("/elasticMgr")
@RestController
public class ElasticMgrController {

    @Autowired
    private BaseElasticService baseElasticService;

    @Autowired
    LocationService locationService;


    /**
     * @Description 新增数据
     * @param elasticDataVo
     * @return xyz.wongs.weathertop.base.message.response.ResponseResult
     * @throws
     * @date 2019/11/20 17:10
     */
    @PostMapping(value = "/add")
    public ResponseResult add(@RequestBody ElasticDataVo elasticDataVo){
        ResponseResult response = getResponseResult();
        try {
            if(!StringUtils.isNotEmpty(elasticDataVo.getIdxName())){
                response.setCode(ResponseCode.PARAM_ERROR_CODE.getCode());
                response.setMsg("索引为空，不允许提交");
                response.setStatus(false);
                log.warn("索引为空");
                return response;
            }
            ElasticEntity elasticEntity = new ElasticEntity();
            elasticEntity.setId(elasticDataVo.getElasticEntity().getId());
            elasticEntity.setData(elasticDataVo.getElasticEntity().getData());

            baseElasticService.insertOrUpdateOne(elasticDataVo.getIdxName(), elasticEntity);

        } catch (Exception e) {
            response.setCode(ResponseCode.ERROR.getCode());
            response.setMsg(ResponseCode.ERROR.getMsg());
            response.setStatus(false);
            log.error("插入数据异常，metadataVo={},异常信息={}", elasticDataVo.toString(),e.getMessage());
        }
        return response;
    }


    /**
     * @Description 删除
     * @param elasticDataVo
     * @return xyz.wongs.weathertop.base.message.response.ResponseResult
     * @throws
     * @date 2019/11/21 9:56
     */
    @PostMapping(value = "/delete")
    public ResponseResult delete(@RequestBody ElasticDataVo elasticDataVo){
        ResponseResult response = getResponseResult();
        try {
            if(!StringUtils.isNotEmpty(elasticDataVo.getIdxName())){
                response.setCode(ResponseCode.PARAM_ERROR_CODE.getCode());
                response.setMsg("索引为空，不允许提交");
                response.setStatus(false);
                log.warn("索引为空");
                return response;
            }
            baseElasticService.deleteOne(elasticDataVo.getIdxName(),elasticDataVo.getElasticEntity());
        } catch (Exception e) {
            log.error("删除数据失败");
        }
        return response;

    }

    /**
     * @Description
     * @param index 初始化Location区域，写入数据。
     * @return xyz.wongs.weathertop.base.message.response.ResponseResult
     * @throws
     * @date 2019/11/20 17:10
     */
    @GetMapping(value = "/addLocation/{index}")
    public ResponseResult addLocation(@PathVariable(value = "index") String index){
        ResponseResult response = getResponseResult();
        try {
            for(int lv=0;lv<4;lv++){
                //addLocationPage(1,100,index,lv);
                addTaskRecordPage(1,100,index,lv);
            }

        } catch (Exception e) {
            response.setCode(ResponseCode.ERROR.getCode());
            response.setMsg("服务忙，请稍后再试");
            response.setStatus(false);
        }
        return response;
    }

    public void addLocationPage(int pageNum,int pageSize,String index,int lv){
        Location location = new Location();
        location.setLv(lv);
        PageHelper.startPage(pageNum, pageSize);
        List<Location> locations = locationService.getList2(location);
        PageInfo pageInfo = new PageInfo(locations);
        if(!pageInfo.getList().isEmpty()){
            log.error("第{}层级，第{}页，开始入ES库",lv,pageNum);
            insertDatas(index,locations);
            if(pageInfo.isHasNextPage()){
                addLocationPage(pageInfo.getNextPage(),pageSize,index,lv);
            }
        }
    }

    public void insertDatas(String idxName,List<Location> locations){
        List<ElasticEntity> elasticEntitys = new ArrayList<ElasticEntity>(locations.size());
        for(Location _loca:locations){
            ElasticEntity elasticEntity = new ElasticEntity();
            elasticEntity.setId(_loca.getId().toString());
//            elasticEntity.setData(gson.toJson(_loca));
//            elasticEntitys.add(elasticEntity);
//            log.error(_loca.toString());
        }
        baseElasticService.insertBatch(idxName,elasticEntitys);
    }

    /**
     * @Description
     * @param queryVo 查询实体对象
     * @return xyz.wongs.weathertop.base.message.response.ResponseResult
     * @throws
     * @date 2019/11/21 9:31
     */
    @GetMapping(value = "/get")
    public ResponseResult get(@RequestBody QueryVo queryVo){

        ResponseResult response = getResponseResult();

        if(!StringUtils.isNotEmpty(queryVo.getIdxName())){
            response.setCode(ResponseCode.PARAM_ERROR_CODE.getCode());
            response.setMsg("索引为空，不允许提交");
            response.setStatus(false);
            log.warn("索引为空");
            return response;
        }

        try {
            Class<?> clazz = ElasticUtil.getClazz(queryVo.getClassName());
            Map<String,Object> params = queryVo.getQuery().get("match");
            Set<String> keys = params.keySet();
            MatchQueryBuilder queryBuilders=null;
            for(String ke:keys){
                queryBuilders = QueryBuilders.matchQuery(ke, params.get(ke));
            }
            if(null!=queryBuilders){
                SearchSourceBuilder searchSourceBuilder = ElasticUtil.initSearchSourceBuilder(queryBuilders);
                List<?> data = baseElasticService.search(queryVo.getIdxName(),searchSourceBuilder,clazz);
                response.setData(data);
            }
        } catch (Exception e) {
            response.setCode(ResponseCode.ERROR.getCode());
            response.setMsg("服务忙，请稍后再试");
            response.setStatus(false);
            log.error("查询数据异常，metadataVo={},异常信息={}", queryVo.toString(),e.getMessage());
        }
        return response;
    }

    public ResponseResult getResponseResult(){
        return new ResponseResult();
    }

    /*------------------------------测试任务-----------------------*/
    @Autowired
    private TaskRecordService recordService;

    public void addTaskRecordPage(int pageNum,int pageSize,String index,int lv){
        PageHelper.startPage(pageNum, pageSize);
        List<TaskRecord> list = recordService.queryAllList();
        PageInfo pageInfo = new PageInfo(list);
        if(!pageInfo.getList().isEmpty()){
            log.error("第{}层级，第{}页，开始入ES库",lv,pageNum);
            insertTaskDatas(index,list);
            if(pageInfo.isHasNextPage()){
                addTaskRecordPage(pageInfo.getNextPage(),pageSize,index,lv);
            }
        }
    }

    public void insertTaskDatas(String idxName,List<TaskRecord> list){
        try{
            List<ElasticEntity> elasticEntitys = new ArrayList<ElasticEntity>(list.size());
            for(TaskRecord _loca : list){
                ElasticEntity elasticEntity = new ElasticEntity();
                elasticEntity.setId(_loca.getId());
                elasticEntity.setData(ObjToMap.objectToMap(_loca));
                elasticEntitys.add(elasticEntity);
            }
            baseElasticService.insertBatch(idxName,elasticEntitys);
        }catch (Exception e){
            e.printStackTrace();
            log.error("error message ... {}",e.getMessage());
        }
    }
    /*---------------------------------------------------------------------------*/

}
