package com.zhliang.springboot.produce.mapper;

import com.zhliang.springboot.produce.entity.BrokerMessageLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author: colin
 * @Date: 2019/9/23 16:37
 * @Description:
 * @Version: V1.0
 */
public interface BrokerMessageLogMapper {


    int insert(BrokerMessageLog record);


    /**
     * 查询 消息状态为：0(发送中) 且已经超时的 消息集合
     * @return
     */
    List<BrokerMessageLog> query4StatusAndTimeoutMessage();


    /**
     * 重新发送统计 count发送次数 +1
     * @param messageId
     * @param updateTime
     */
    void update4ReSend(@Param("messageId")String messageId,@Param("updateTime") Date updateTime);

    /**
     * 更新消息最终发送结果 成功 ors 失败
     * @param messageId
     * @param status
     * @param updateTime
     */
    void changeBrokerMessageLogStatus(@Param("messageId")String messageId,@Param("status")String status,
                                      @Param("updateTime") Date updateTime);
}
