package com.background.manager.model.dto.request.workOrder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("回复工单内容请求体")
public class ReplyWorkOrderMessageRequest {

    @ApiModelProperty("工单ID编号")
    private  String workOrderId;

    @ApiModelProperty("消息类型，0文字，1图片，2语音，3视频")
    private Integer messageType;

    @ApiModelProperty("文字消息内容")
    private String messageText;

    @ApiModelProperty("消息类型非文字时文件存储路径")
    private String messageUrl;

}
