package com.zhliang.springboot.elasticsearch.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 任务轨迹表(TaskRecord)实体类
 *
 * @author makejava
 * @since 2020-12-23 17:05:55
 */
@AllArgsConstructor
@NoArgsConstructor
public class TaskRecord implements Serializable {
    private static final long serialVersionUID = 849780649372603242L;
    
    private String id;
    /**
    * 任务号(同一任务,不同状态:任务号是相同的)
    */
    private String taskNo;
    /**
    * 报案号
    */
    private String reportNo;
    /**
    * 立案号
    */
    private String caseNo;
    /**
    * 车牌号
    */
    private String licenseNo;
    /**
    * 上一个任务节点任务ID
    */
    private String preTaskId;
    /**
    * 损失名称
    */
    private String lossName;
    /**
    * 平台代码(003调度,004查勘,005定损,006核价,007核损,008复勘,009复勘审核,010单证,011理算,012核赔,013结案,014重开赔案,,015损余,016诉讼,017追偿,018风险预警发起  019风控调查处理 020 风险调查审核 022 预赔 )
    */
    private String taskType;
    /**
    * 任务类型编码(000整案	001标的车 002三者车 003物损 005车上司机 006车上乘客 007三者人伤 010交强险 011商业险 	)
    */
    private String taskClass;
    /**
    * 任务状态(001待处理 002处理中 003改派 009完成 007核价退回 017核损退回 027复勘审核退回 037核赔退回 047理算退回 004核价上报 014核损上报 024核赔上报 057风险调查退回)
    */
    private String taskStatus;
    /**
    * 定损任务类型：1正常定损,2预估损失,3预估转正常定损,4追加定损,5新增定损
    */
    private String evalType;
    /**
    * 叫号标识 3核价退回,4核损退回,5复勘审核退回
    */
    private String callSign;
    /**
    * 任务子状态（平台代码+任务类型+任务状态）
    */
    private String childStatus;
    /**
    * 是否异地出险：0否,1是；
    */
    private String otherPlaceDamage;
    /**
    * 是否上锁 0否；1是
    */
    private String lockStatus;
    /**
    * 任务接收状态（0未接收,1已接收 2已拒接）
    */
    private String receiveStatus;
    /**
    * 是否委托 0-否 1-是
    */
    private String trustFlag;
    /**
    * 加急状态 0否 1是 2 取消
    */
    private String urgentStatus;
    /**
    * 保单NO(POLICY_UK多个用逗号分隔)
    */
    private String policyNos;
    /**
    * 被保险人名称
    */
    private String insuredName;
    /**
    * 承保机构
    */
    private String comCode;
    /**
    * 承保机构名称
    */
    private String comName;
    /**
    * 理赔（受理）机构
    */
    private String claimDoingInsCode;
    /**
    * 关联调度任务ID
    */
    private String dispatchTaskId;
    /**
    * 办理/调度人工号
    */
    private String doUserNo;
    /**
    * 办理/调度人名称
    */
    private String doUserName;
    /**
    * 办理/调度人手机号
    */
    private String doUserPhone;
    /**
    * 办理/调度人组编号
    */
    private String doGroupNo;
    /**
    * 办理/调度人组名称
    */
    private String doGroupName;
    /**
    * 办理/调度人机构代码
    */
    private String doCompanyCode;
    /**
    * 办理/调度人机构名称
    */
    private String doCompanyName;
    /**
    * 进入日期（流入日期）
    */
    //@JSONField(format="yyyy-MM-dd HH:mm:ss")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date intoTime;
    /**
    * 办理时间(开始处理)
    */
    //@JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date doTime;
    /**
    * 结束时间(流出时间)
    */
    //@JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date finishedTime;
    /**
    * 等级id
    */
    private String gradeId;
    /**
    * 等级名称
    */
    private String gradeName;
    /**
    * 等级值
    */
    private String gradeValue;
    /**
    * 删除标记 0正常,1删除
    */
    private String delFlag;
    /**
    * 创建人ID
    */
    private String createUserId;
    /**
    * 创建名称
    */
    private String createUserName;
    /**
    * 创建时间
    */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    //@JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
    * 修改人ID
    */
    private String updateUserId;
    /**
    * 修改人名称
    */
    private String updateUserName;
    /**
    * 修改时间
    */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
    * 出险时间
    */
    //@JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date damageDate;
    /**
    * 报案时间
    */
    //@JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date reportDate;

    /**
    * 保险公司代码
    */
    private String insuranceCode;
    /**
    * 保险公司名称
    */
    private String insuranceName;
    /**
    * 原因代码（改派、新增、定损、追加等原因）
    */
    private String reasonCode;
    /**
    * 原因（改派、新增、定损、追加等原因）
    */
    private String reasonName;
    /**
    * 批次号(首次为0,每改派一次增加1)
    */
    private Integer batchNumber;
    /**
    * 重开次数(首次为0,每重开一次增加1)
    */
    private Integer reopenNumber;
    /**
    * 案件性质1:重大案件、2:自助、3:一般
    */
    private String caseNature;
    /**
    * 金额
    */
    private Double claimMoney;
    /**
    * 省code
    */
    private String provinceCode;
    /**
    * 市code
    */
    private String cityCode;
    /**
    * 区code
    */
    private String areaCode;
    /**
    * 详细地址
    */
    private String detailedAddress;
    /**
    * 查勘损失ID
    */
    private String involvedCarId;
    /**
    * 任务有效状态 0-有效 1-临时 2-注销
    */
    private String validState;
    /**
    * 车架号(VIN码)
    */
    private String vinNo;
    /**
    * 发动机号
    */
    private String engineNo;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getPreTaskId() {
        return preTaskId;
    }

    public void setPreTaskId(String preTaskId) {
        this.preTaskId = preTaskId;
    }

    public String getLossName() {
        return lossName;
    }

    public void setLossName(String lossName) {
        this.lossName = lossName;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskClass() {
        return taskClass;
    }

    public void setTaskClass(String taskClass) {
        this.taskClass = taskClass;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getEvalType() {
        return evalType;
    }

    public void setEvalType(String evalType) {
        this.evalType = evalType;
    }

    public String getCallSign() {
        return callSign;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    public String getChildStatus() {
        return childStatus;
    }

    public void setChildStatus(String childStatus) {
        this.childStatus = childStatus;
    }

    public String getOtherPlaceDamage() {
        return otherPlaceDamage;
    }

    public void setOtherPlaceDamage(String otherPlaceDamage) {
        this.otherPlaceDamage = otherPlaceDamage;
    }

    public String getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(String lockStatus) {
        this.lockStatus = lockStatus;
    }

    public String getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(String receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public String getTrustFlag() {
        return trustFlag;
    }

    public void setTrustFlag(String trustFlag) {
        this.trustFlag = trustFlag;
    }

    public String getUrgentStatus() {
        return urgentStatus;
    }

    public void setUrgentStatus(String urgentStatus) {
        this.urgentStatus = urgentStatus;
    }

    public String getPolicyNos() {
        return policyNos;
    }

    public void setPolicyNos(String policyNos) {
        this.policyNos = policyNos;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public String getComCode() {
        return comCode;
    }

    public void setComCode(String comCode) {
        this.comCode = comCode;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getClaimDoingInsCode() {
        return claimDoingInsCode;
    }

    public void setClaimDoingInsCode(String claimDoingInsCode) {
        this.claimDoingInsCode = claimDoingInsCode;
    }

    public String getDispatchTaskId() {
        return dispatchTaskId;
    }

    public void setDispatchTaskId(String dispatchTaskId) {
        this.dispatchTaskId = dispatchTaskId;
    }

    public String getDoUserNo() {
        return doUserNo;
    }

    public void setDoUserNo(String doUserNo) {
        this.doUserNo = doUserNo;
    }

    public String getDoUserName() {
        return doUserName;
    }

    public void setDoUserName(String doUserName) {
        this.doUserName = doUserName;
    }

    public String getDoUserPhone() {
        return doUserPhone;
    }

    public void setDoUserPhone(String doUserPhone) {
        this.doUserPhone = doUserPhone;
    }

    public String getDoGroupNo() {
        return doGroupNo;
    }

    public void setDoGroupNo(String doGroupNo) {
        this.doGroupNo = doGroupNo;
    }

    public String getDoGroupName() {
        return doGroupName;
    }

    public void setDoGroupName(String doGroupName) {
        this.doGroupName = doGroupName;
    }

    public String getDoCompanyCode() {
        return doCompanyCode;
    }

    public void setDoCompanyCode(String doCompanyCode) {
        this.doCompanyCode = doCompanyCode;
    }

    public String getDoCompanyName() {
        return doCompanyName;
    }

    public void setDoCompanyName(String doCompanyName) {
        this.doCompanyName = doCompanyName;
    }

    public Date getIntoTime() {
        return intoTime;
    }

    public void setIntoTime(Date intoTime) {
        this.intoTime = intoTime;
    }

    public Date getDoTime() {
        return doTime;
    }

    public void setDoTime(Date doTime) {
        this.doTime = doTime;
    }

    public Date getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(Date finishedTime) {
        this.finishedTime = finishedTime;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(String gradeValue) {
        this.gradeValue = gradeValue;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getDamageDate() {
        return damageDate;
    }

    public void setDamageDate(Date damageDate) {
        this.damageDate = damageDate;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getInsuranceCode() {
        return insuranceCode;
    }

    public void setInsuranceCode(String insuranceCode) {
        this.insuranceCode = insuranceCode;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getReasonName() {
        return reasonName;
    }

    public void setReasonName(String reasonName) {
        this.reasonName = reasonName;
    }

    public Integer getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(Integer batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Integer getReopenNumber() {
        return reopenNumber;
    }

    public void setReopenNumber(Integer reopenNumber) {
        this.reopenNumber = reopenNumber;
    }

    public String getCaseNature() {
        return caseNature;
    }

    public void setCaseNature(String caseNature) {
        this.caseNature = caseNature;
    }

    public Double getClaimMoney() {
        return claimMoney;
    }

    public void setClaimMoney(Double claimMoney) {
        this.claimMoney = claimMoney;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public String getInvolvedCarId() {
        return involvedCarId;
    }

    public void setInvolvedCarId(String involvedCarId) {
        this.involvedCarId = involvedCarId;
    }

    public String getValidState() {
        return validState;
    }

    public void setValidState(String validState) {
        this.validState = validState;
    }

    public String getVinNo() {
        return vinNo;
    }

    public void setVinNo(String vinNo) {
        this.vinNo = vinNo;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

}