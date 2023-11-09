package com.background.manager.model.dto.request.receipt;

import com.background.manager.constants.database.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("分页查询用户开票记录DTO")
public class PageQueryReceiptRecordRequest extends BasePageDTO {

    @ApiModelProperty("收件人")
    private String receiver;

    @ApiModelProperty("发票类型(0-普票，1-专票)")
    private Integer type;

    @ApiModelProperty("发票抬头")
    private String title;

    @ApiModelProperty("备注")
    private String remarks;

}
