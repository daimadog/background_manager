package com.background.manager.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("典型案例实体类")
public class BackgroundTypicalClass {

    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("主标题")
    private String title;
    @ApiModelProperty("副标题")
    private String subtitle;
    @ApiModelProperty("图片url地址")
    private String imageUrl;
    @ApiModelProperty("内容")
    private String context;
    @ApiModelProperty("外部链接")
    private String connectUrl;

}
