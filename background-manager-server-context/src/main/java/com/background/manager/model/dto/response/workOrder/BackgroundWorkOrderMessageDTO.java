package com.background.manager.model.dto.response.workOrder;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@ApiModel("工单历史回复")
public class BackgroundWorkOrderMessageDTO {

    @ApiModelProperty("消息编号")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("消息方向，0-从用户到运营，1-从运营到用户")
    private Integer direction;

    @ApiModelProperty("文字消息内容")
    private String messageText;

    @ApiModelProperty("发送时间")
    private LocalDateTime createTime;


}
