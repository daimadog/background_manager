package com.background.manager.dto.request.user;

import com.background.manager.constants.database.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("分页查询用户企业认证信息请求体")
public class PageQueryUserEnterpriseCertificationRequest extends BasePageDTO {

    @ApiModelProperty(value = "用户id",required = false)
    private String userId;

    @ApiModelProperty(value = "企业名称",required = false)
    private String companyName;

    @ApiModelProperty(value = "认证状态",required = false)
    private Integer certificationStatus;

}
