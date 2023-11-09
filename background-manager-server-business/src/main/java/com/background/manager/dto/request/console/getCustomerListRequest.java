package com.background.manager.dto.request.console;

import com.background.manager.constants.database.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("客户管理列表请求体")
public class getCustomerListRequest extends BasePageDTO {
    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("联系人")
    private String contactName;

    @ApiModelProperty("联系电话")
    private String contactPhone;

}
