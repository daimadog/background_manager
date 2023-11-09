package com.background.manager.model.dto.request.workOrder;

import com.background.manager.constants.database.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("工单列表请求体")
public class PageQueryBackgroundWorkOrderRequest extends BasePageDTO {

    @ApiModelProperty("工单标题")
    private String title;

    @ApiModelProperty("工单类型-0商务类，1技术类，2厂商类，3其他")
    private Integer type;

    @ApiModelProperty("工单状态-0待处理，1处理中，2工单关闭")
    private Integer status;

    @ApiModelProperty("工单提交人")
    private String accountId;

}
