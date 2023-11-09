package com.background.manager.model.dto.request.column;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("修改栏目请求体")
public class ModifyColumnRequest {

    @ApiModelProperty("栏目编号")
    private Long id;

    @ApiModelProperty("栏目名称")
    private String name;

    @ApiModelProperty("栏目英文名")
    private  String enName;

    @ApiModelProperty("上级栏目Id")
    private  Long parentId;

    @ApiModelProperty("栏目描述")
    private  String description;

    @ApiModelProperty("栏目状态")
    private Integer status;

    @ApiModelProperty("模板链接")
    private  String templatePath;

    @ApiModelProperty("栏目路径地址")
    private String path;

    @ApiModelProperty("是否展示（0-展示,1-不展示）")
    private Integer onTap;

}
