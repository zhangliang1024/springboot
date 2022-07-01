package com.zhliang.springboot.elasticsearch.controller;

import com.alibaba.fastjson.JSON;
import com.zhliang.springboot.elasticsearch.entity.TaskRecord;
import com.zhliang.springboot.elasticsearch.response.PageDTO;
import com.zhliang.springboot.elasticsearch.service.BaseElasticService;
import com.zhliang.springboot.elasticsearch.utils.DateUtil;
import com.zhliang.springboot.elasticsearch.utils.ElasticUtil;
import com.zhliang.springboot.elasticsearch.utils.StringUtils;
import com.zhliang.springboot.elasticsearch.vo.ThemeVo;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.*;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.CardinalityAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @类描述：任务主题查询
 * @创建人：zhiang
 * @创建时间：2020/12/23 17:47
 * @version：V1.0
 */
@RestController
@RequestMapping("theme")
public class CommonThemeController {

    @Autowired
    BaseElasticService baseElasticService;

    /**
     * elasticsearch在通过script脚本进行时差判断时,目前服务器有时差问题
     * 1. 通过script脚本拿到的时间戳，要比实际时间多8小时
     * 2. script脚本获取的当前时间戳是正确的
     * 3. 目前超时阈值为 24小时。
     * 4. 在实际比较中，因为script脚本是通过时间戳 毫秒去比较的
     * 5. 所以比较中真是的阈值为 ：16小时
     */
    public static final int DEFAULT_OVER_TIME = 24 * 60 * 60 * 1000 ; //86400000
    public static final int TIME_DIFFERENCE = 8 * 60 *60 * 1000;      //28800000
    public static final int REAL_OVER_TIME = 16 * 60 *60 * 1000;      //57600000


    private BoolQueryBuilder commonParams(ThemeVo vo) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        /**平台编码**/
        if (StringUtils.isNotBlank(vo.getTaskType())) {
            queryBuilder.must(QueryBuilders.termQuery("task_type", vo.getTaskType()));
        }
        /**任务状态**/
        if (StringUtils.isNotBlank(vo.getTaskStatus())) {
            queryBuilder.must(QueryBuilders.termQuery("task_status", vo.getTaskStatus()));
        }


        /**报案号**/
        if (StringUtils.isNotBlank(vo.getReportNo())) {
            queryBuilder.must(QueryBuilders.termQuery("report_no", vo.getReportNo()));
        }
        /**任务号**/
        if (StringUtils.isNotBlank(vo.getTaskNo())) {
            queryBuilder.must(QueryBuilders.termQuery("task_no", vo.getTaskNo()));
        }
        /**立案号**/
        if (StringUtils.isNotBlank(vo.getCaseNo())) {
            queryBuilder.must(QueryBuilders.termQuery("case_no", vo.getCaseNo()));
        }


        /**车牌号**/
        if (StringUtils.isNotBlank(vo.getLicenseNo())) {
            queryBuilder.must(QueryBuilders.termQuery("license_no", vo.getLicenseNo()));
        }
        /**发动机号**/
        if (StringUtils.isNotBlank(vo.getEngineNo())) {
            queryBuilder.must(QueryBuilders.termQuery("engine_no", vo.getEngineNo()));
        }
        /**VIN码**/
        if (StringUtils.isNotBlank(vo.getVinNo())) {
            queryBuilder.must(QueryBuilders.termQuery("vin_no", vo.getVinNo()));
        }


        /**保单号**/
        if (StringUtils.isNotBlank(vo.getPolicyNo())) {
            StringBuilder sb = new StringBuilder();
            sb.append("*").append(vo.getPolicyNo()).append("*");
            queryBuilder.must(QueryBuilders.wildcardQuery("policy_nos", sb.toString()));
        }
        /**被保险人**/
        if (StringUtils.isNotBlank(vo.getInsuredName())) {
            StringBuilder sb = new StringBuilder();
            sb.append("*").append(vo.getInsuredName()).append("*");
            queryBuilder.must(QueryBuilders.wildcardQuery("insured_name", sb.toString()));
        }
        /**案件性质**/
        if (StringUtils.isNotBlank(vo.getCaseNature())) {
            queryBuilder.must(QueryBuilders.termQuery("case_nature", vo.getCaseNature()));
        }
        /**定损任务类型**/
        if (StringUtils.isNotBlank(vo.getEvalType())) {
            queryBuilder.must(QueryBuilders.termQuery("eval_type", vo.getEvalType()));
        }


        /**是否委托**/
        if (StringUtils.isNotBlank(vo.getTrustFlag())) {
            queryBuilder.must(QueryBuilders.termQuery("trust_flag", vo.getTrustFlag()));
        }
        /**是否异地**/
        if (StringUtils.isNotBlank(vo.getOtherPlaceDamage())) {
            queryBuilder.must(QueryBuilders.termQuery("other_place_damage", vo.getOtherPlaceDamage()));
        }
        /**是否加急**/
        if (StringUtils.isNotBlank(vo.getUrgentStatus())) {
            queryBuilder.must(QueryBuilders.termQuery("urgent_status", vo.getUrgentStatus()));
        }


        /**理赔机构**/
        if (!CollectionUtils.isEmpty(vo.getClaimDoingInsCode())) {
            queryBuilder.must(QueryBuilders.termsQuery("claim_doing_ins_code", vo.getClaimDoingInsCode()));
        }
        /**承保机构**/
        if (!CollectionUtils.isEmpty(vo.getComCode())) {
            queryBuilder.must(QueryBuilders.termsQuery("com_code", vo.getComCode()));
        }


        /**结束(流出)时间**/
        if (StringUtils.isNotNull(vo.getFinishedStartTime()) || StringUtils.isNotNull(vo.getFinishedEndTime())) {
            queryBuilder.filter(QueryBuilders.rangeQuery("finished_time").gte(vo.getFinishedStartTime()).lte(vo.getFinishedEndTime()));
        }
        /**调度(流入)时间**/
        if (StringUtils.isNotNull(vo.getDispatchStartTime()) || StringUtils.isNotNull(vo.getDispatchEndTime())) {
            queryBuilder.filter(QueryBuilders.rangeQuery("into_time").gte(vo.getDispatchStartTime()).lte(vo.getDispatchEndTime()));
        }
        /**报案时间**/
        if (StringUtils.isNotNull(vo.getReportStartDate()) || StringUtils.isNotNull(vo.getReportEndDate())) {
            queryBuilder.filter(QueryBuilders.rangeQuery("report_date").gte(vo.getReportStartDate()).lte(vo.getReportEndDate()));
        }
        /**出险时间**/
        if (StringUtils.isNotNull(vo.getDamageStartDate()) || StringUtils.isNotNull(vo.getDamageEndDate())) {
            queryBuilder.filter(QueryBuilders.rangeQuery("damage_date").gte(vo.getDamageStartDate()).lte(vo.getDamageEndDate()));
        }


        //TODO 查询已完成任务



        String scriptStr = "";
        if("009".equals(vo.getTaskStatus())){
            /**已完成 是否超时**/
            if ("1".equals(vo.getIsTimeOut())) {
                scriptStr = "if (doc['finished_time'].size()==0 || doc['into_time'].size()==0) {return false} else {return doc['finished_time'].value.toInstant().toEpochMilli() - doc['into_time'].value.toInstant().toEpochMilli() > 86400000}";
            } else if ("0".equals(vo.getIsTimeOut())) {
                scriptStr = "if (doc['finished_time'].size()==0 || doc['into_time'].size()==0) {return false} else {return doc['finished_time'].value.toInstant().toEpochMilli() - doc['into_time'].value.toInstant().toEpochMilli() < 86400000}";
            }
        }else {
            /**待处理处理中 是否超时**/
            if ("1".equals(vo.getIsTimeOut())) {
                //scriptStr = "if (doc['into_time'].size()==0) {return false} else {return new Date().getTime() - doc['into_time'].value.toInstant().toEpochMilli() > 86400000}";
                queryBuilder.filter(QueryBuilders.rangeQuery("into_time").lte(DateUtil.getYesterdayTime()));
            } else if ("0".equals(vo.getIsTimeOut())) {
                //scriptStr = "if (doc['into_time'].size()==0) {return false} else {return new Date().getTime() - doc['into_time'].value.toInstant().toEpochMilli() < 86400000}";
                queryBuilder.filter(QueryBuilders.rangeQuery("into_time").gte(DateUtil.getYesterdayTime()));
            }
        }
        if (StringUtils.isNotBlank(scriptStr)) {
            Map<String, Object> params = new HashMap<>();
            Script script = new Script(ScriptType.INLINE, "painless", scriptStr, params);
            queryBuilder.must(QueryBuilders.scriptQuery(script));
        }

        /**FIXME 当前用户**/
        if (StringUtils.isNotBlank(vo.getDoUserNo())) {
            queryBuilder.must(QueryBuilders.termQuery("do_user_no", vo.getDoUserNo()));
        }
        if (!CollectionUtils.isEmpty(vo.getTaskClass())) {
            queryBuilder.must(QueryBuilders.termsQuery("task_class", vo.getTaskClass()));
        }

        /**是否删除、是否有效**/
        //queryBuilder.must(QueryBuilders.termQuery("del_flag", "0"));
        //queryBuilder.must(QueryBuilders.termQuery("valid_state", "0"));

        return queryBuilder;
    }


    @PostMapping("list")
    public PageDTO themeList(@RequestBody ThemeVo vo) {
        BoolQueryBuilder builder = commonParams(vo);
        //String param = builder.toString();
        //System.out.println("====>>> \r\nquery " + param);


        //FieldSortBuilder order = SortBuilders.fieldSort("create_time").order(SortOrder.ASC);
        Class<?> clazz = ElasticUtil.getClazz("com.zhliang.springboot.elasticsearch.entity.TaskRecord");
        int from = (vo.getPageNum().intValue() -1 ) * (vo.getPageSize().intValue());
        SearchSourceBuilder searchSourceBuilder = ElasticUtil.initSearchSourceBuilder(builder, from, vo.getPageSize());
        //searchSourceBuilder.sort(order);

        //TODO 查询已完成业务数据
        //TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("groupBytask").field("task_no").size(20);
        //aggregationBuilder.subAggregation(AggregationBuilders.topHits("current_task").sort(SortBuilders.fieldSort("create_time").order(SortOrder.DESC)).size(1));
        //searchSourceBuilder.aggregation(aggregationBuilder);
        //PageDTO data = baseElasticService.searchFinishedCopy("theme_task", searchSourceBuilder, clazz);

        //TODO 查询已完成业务数据total
        //SearchSourceBuilder searchTotalBuilder = ElasticUtil.initSearchSourceBuilder(builder, from, vo.getPageSize());
        //CardinalityAggregationBuilder totalBuilder = AggregationBuilders.cardinality("total_task").field("task_no");
        //searchTotalBuilder.aggregation(totalBuilder);
        //long total = baseElasticService.searchTotal("theme_task", searchTotalBuilder);
        //data.setTotal(total);

        PageDTO data = baseElasticService.searchCopy("theme_task", searchSourceBuilder, clazz);

        data.setPageNum(vo.getPageNum());
        data.setPageSize(vo.getPageSize());

        return data;
    }


    @PostMapping("copy/list")
    public PageDTO copyList(@RequestBody ThemeVo vo) {

        commonParams(vo);

        //复合查询
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        //精确查询
        queryBuilder.must(QueryBuilders.termQuery("task_type", vo.getTaskType()));
        queryBuilder.must(QueryBuilders.termQuery("task_status", vo.getTaskStatus()));
        queryBuilder.must(QueryBuilders.termsQuery("task_class", Arrays.asList("001", "002")));
        //queryBuilder.must(QueryBuilders.termQuery("do_user_no", "06011012"));
        //查询字段为空
        queryBuilder.mustNot(QueryBuilders.existsQuery("do_user_no"));
        queryBuilder.mustNot(QueryBuilders.existsQuery("call_sign"));
        //查询字段不为空
        queryBuilder.filter(QueryBuilders.existsQuery("call_sign"));
        //时间 大于小于某个值
        queryBuilder.must(QueryBuilders.rangeQuery("create_time").gte("2020-12-19 00:00:00").lte("2020-12-21 23:59:59"));
        queryBuilder.filter(QueryBuilders.rangeQuery("create_time").gte("2020-12-18T00:00:00.000Z").lte("2020-12-21T23:59:59.000Z"));
        //排序
        FieldSortBuilder order = SortBuilders.fieldSort("create_time").order(SortOrder.ASC);
        Class<?> clazz = ElasticUtil.getClazz("com.zhliang.springboot.elasticsearch.entity.TaskRecord");
        int from = (vo.getPageNum().intValue()) * (vo.getPageSize().intValue());
        SearchSourceBuilder searchSourceBuilder = ElasticUtil.initSearchSourceBuilder(queryBuilder, from, vo.getPageSize());
        searchSourceBuilder.sort(order);

        PageDTO data = baseElasticService.searchCopy("theme_task", searchSourceBuilder, clazz);
        data.setPageNum(vo.getPageNum());
        data.setPageSize(vo.getPageSize());
        return data;
    }

    @PostMapping("list/response")
    public ResponseEntity responseEntity(ThemeVo vo) throws Exception {
        XContentFactory.jsonBuilder().startObject().field("name", vo.getCaseNo()).endObject();
        return new ResponseEntity(null, HttpStatus.OK);
    }


}
