package com.background.manager.model.dto.response.workOrder;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@ApiModel("工单列表DTO")
public class BackgroundWorkOrderDigestDTO {

    @ApiModelProperty("工单编号")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty("工单标题")
    private String title;

    @ApiModelProperty("工单内容")
    private String content;

    @ApiModelProperty("工单类型-0商务类，1技术类，2厂商类，3其他")
    private Integer type;

    @ApiModelProperty("工单状态-0待处理，1处理中，2工单关闭")
    private Integer status;

    @ApiModelProperty("工单提交人(用户)")
    private String username;

    @ApiModelProperty("工单提交时间(用户)")
    private LocalDateTime submitTime;

}
