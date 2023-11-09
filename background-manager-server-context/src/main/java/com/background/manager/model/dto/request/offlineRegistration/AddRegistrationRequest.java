package com.background.manager.model.dto.request.offlineRegistration;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
@ApiModel("提交离线登记请求体")
public class AddRegistrationRequest {

    @ApiModelProperty("姓名")
    @NotBlank(message = "姓名不能为空")
    private String name;

    @ApiModelProperty("公司名称/学校名称")
    @NotBlank(message = "公司名称/学校名称不能为空")
    private String company;

    @ApiModelProperty("职位")
    @NotBlank(message = "职位不能为空")
    private String  status;

    @ApiModelProperty("手机号")
    @NotBlank(message = "手机号不能为空")
    private String phone;

    @ApiModelProperty("邮箱地址")
    @NotBlank(message = "邮箱地址不能为空")
    private String email;

    @ApiModelProperty("离线登记内容")
    private String messageContent;
}
