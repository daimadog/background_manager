package com.background.manager.model.dto.response.template;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@ApiModel("合同模板DTO")
public class ContractTemplateDTO {

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("合同模板名称")
    private String name;

    @ApiModelProperty("模板描述")
    private String description;

    @ApiModelProperty("合同模板状态0-不可下载,1-可下载")
    private Integer status;

    @ApiModelProperty("模板附件地址")
    private String templateUrl;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;


}



