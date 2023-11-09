package com.background.manager.dto.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Author: 杜黎明
 * @Date: 2023/03/22 13:28:57
 * @Version: 1.0.0
 */
@Data
@ApiModel("审核用户企业认证信息请求体")
public class applyUserEnterpriseCertificationRequest {

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("企业认证信息状态(1-同意,2-拒绝)")
    private Integer certificationStatus;

}
