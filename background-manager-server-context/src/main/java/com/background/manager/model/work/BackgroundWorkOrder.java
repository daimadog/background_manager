package com.background.manager.model.work;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("工单管理实体类")
public class BackgroundWorkOrder {

    @ApiModelProperty("主键ID")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty("所属账户ID")
    private String accountId;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("工单内容")
    private String content;

    @ApiModelProperty("类型-0商务类，1技术类，2厂商类，3其他")
    private Integer type;

    @ApiModelProperty("工单状态-0待处理，1处理中，2工单关闭")
    private Integer status;

    @ApiModelProperty("提交时间")
    private LocalDateTime submitTime;

    @ApiModelProperty("处理时间")
    private LocalDateTime handleTime;

    @ApiModelProperty("处理人id")
    private String handleBy;

    @ApiModelProperty("完成时间")
    private LocalDateTime completeTime;


}
