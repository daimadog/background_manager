package com.background.manager.model.dto.request.order;

import com.background.manager.constants.database.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("分页查询AI订单申请记录请求体")
public class pageQueryAiOrderRequest extends BasePageDTO {


    @ApiModelProperty("订单编号")
    private String orderSn;

    @ApiModelProperty("产品")
    private String productName;

    @ApiModelProperty("申请状态")
    private Integer applyStatus;

}
