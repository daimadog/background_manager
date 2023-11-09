package com.background.manager.dto.response.log;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@ApiModel("分页查询操作日志DTO")
public class OperationLogDigestDTO  {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("操作日志名称")
    private String requestName;

    @ApiModelProperty("请求API接口")
    private String requestApi;

    @ApiModelProperty("请求方式")
    private String requestType;

    @ApiModelProperty("创建人")
    private String creator;

    @ApiModelProperty("创建Ip")
    private  String creatorIp;

    @ApiModelProperty("创建地址")
    private  String creatorAddress;

    @ApiModelProperty("花费时间")
    private Integer costTime;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("操作日志状态")
    private Integer requestStatus;

    @ApiModelProperty("返回的信息描述")
    private String resMsg;
}
