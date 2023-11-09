package com.background.manager.model.dto.request.receipt;

import com.background.manager.constants.database.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.sql.Date;

@Data
@ApiModel("分页查询发票请求体")
public class PageQueryReceiptRequest  extends BasePageDTO {

    @ApiModelProperty("开票时间")
    private Date receiptTime;

    @ApiModelProperty("申请账户")
    private String account;

    @ApiModelProperty("发票类型(0-普票，1-专票)")
    private Integer type;

    @ApiModelProperty("发票状态")
    private Integer status;

    @ApiModelProperty("发票抬头")
    private String title;

    @ApiModelProperty("备注")
    private String remarks;
}
