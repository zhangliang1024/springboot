package com.zhliang.springboot.elasticsearch.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @创建人：zhiang
 * @创建时间：2020/12/23 17:49
 * @version：V1.0
 */
@Data
public class ThemeVo {

    private String reportNo;
    private String licenseNo;
    private String policyNo;
    private String insuredName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishedStartTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishedEndTime;
    private Date dispatchStartTime;
    private Date dispatchEndTime;
    private Date reportStartDate;
    private Date reportEndDate;

    private Date damageStartDate;
    private Date damageEndDate;

    private String taskType;
    private String evalType;
    private String taskStatus;
    private String isTimeOut;
    private String trustFlag;
    private String order;
    private Integer isMobileTerminal;

    private String vinNo;
    private String engineNo;

    private String featurePage;
    private String acceptTask;

    private String taskNo;
    private List<String> claimDoingInsCode;
    private List<String> comCode;
    private String caseNo;
    private String caseNature;
    private String isReopen;
    private String isQuick;


    private Integer pageNum;
    private Integer pageSize;


    private String otherPlaceDamage;
    private String urgentStatus;

    private String doUserNo;
    private List<String> taskClass;


}
