package com.background.manager.model.work;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@ApiModel("工单消息")
public class BackgroundWorkOrderMessage {

    @ApiModelProperty("主键ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("工单ID编号")
    private  String workOrderId;

    @ApiModelProperty("消息方向，0-从用户到运营，1-从运营到用户")
    private Integer direction;

    @ApiModelProperty("消息类型，0文字，1图片，2语音，3视频")
    private Integer messageType;

    @ApiModelProperty("文字消息内容")
    private String messageText;

    @ApiModelProperty("消息类型非文字时文件存储路径")
    private String messageUrl;

    @ApiModelProperty("发送人id")
    private String createBy;

    @ApiModelProperty("发送时间")
    private LocalDateTime createTime;

}
