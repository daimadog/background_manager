package com.background.manager.model.dto.response.workOrder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel("工单详情DTO")
public class BackgroundWorkOrderDTO {

    @ApiModelProperty("工单编号")
    private String id;

    @ApiModelProperty("工单所属账户(用户)")
    private String username;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("类型-0商务类，1技术类，2厂商类，3其他")
    private Integer type;

    @ApiModelProperty("工单状态-0待处理，1处理中，2工单关闭")
    private Integer status;

    @ApiModelProperty("提交时间")
    private LocalDateTime submitTime;

    @ApiModelProperty("处理人(运营人员)")
    private String handleBy;

    @ApiModelProperty("工单内容")
    private String content;

    @ApiModelProperty("历史交流记录")
    private List<BackgroundWorkOrderMessageDTO> messageList;


}
