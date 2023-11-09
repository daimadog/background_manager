package com.background.manager.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description: 操作日志领域模型
 * @Author: 杜黎明
 * @Date: 2022/10/17 09:01:56
 * @Version: 1.0.0
 */
@Data
@ApiModel("操作日志")
@Builder
public class BackgroundOperationLog {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 请求API接口
     */
    private String requestApi;
    /**
     * 请求方式
     */
    private String requestType;
    /**
     * 请求参数
     */
    private String requestParam;
    /**
     * 请求Token
     */
    private String requestToken;
    /**
     * 请求方式名称
     */
    private String requestName;
    /**
     * 请求状态（0-成功，1-失败）
     */
    private Integer requestStatus;
    /**
     * 返回状态
     */
    private String resCode;
    /**
     * 返回信息描述
     */
    private  String resMsg;
    /**
     * 返回参数
     */
    private  String resJson;
    /**
     * 请求开始时间
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private LocalDateTime startTime;
    /**
     * 请求结束时间
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private LocalDateTime endTime;
    /**
     * 花费时间
     */
    private Integer costTime;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建Ip
     */
    private  String creatorIp;
    /**
     * 创建地址
     */
    private  String creatorAddress;
    /**
     * 创建时间
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @TableField(
            fill = FieldFill.INSERT
    )
    private  LocalDateTime createTime;

}
