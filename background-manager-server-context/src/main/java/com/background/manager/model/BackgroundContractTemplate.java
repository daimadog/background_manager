package com.background.manager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("合同模板")
public class BackgroundContractTemplate extends ContextBasePO{

    @ApiModelProperty("主键ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("合同模板名称")
    private String name;

    @ApiModelProperty("模板描述")
    private String description;

    @ApiModelProperty("模板附件地址")
    private String templateUrl;

    @ApiModelProperty("合同模板状态0-不可下载,1-可下载")
    private Integer status;

}
