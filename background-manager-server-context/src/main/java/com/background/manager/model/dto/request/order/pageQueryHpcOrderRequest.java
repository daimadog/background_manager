package com.background.manager.model.dto.request.order;

import com.background.manager.constants.database.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("分页查询HPC订单申请列表请求体")
public class pageQueryHpcOrderRequest extends BasePageDTO {

    @ApiModelProperty("订单号")
    private String orderId;

    @ApiModelProperty("操作类型")
    private Integer applyStatus;

}
