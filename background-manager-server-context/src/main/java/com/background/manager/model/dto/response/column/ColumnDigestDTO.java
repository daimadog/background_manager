package com.background.manager.model.dto.response.column;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel("栏目首页展示请求体")
public class ColumnDigestDTO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("栏目名称")
    private String name;

    @ApiModelProperty("栏目英文名")
    private  String enName;

    @ApiModelProperty("上级栏目Id")
    private  Long parentId;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("栏目路径地址")
    private String path;

    @ApiModelProperty("是否展示（0-展示,1-不展示）")
    private Integer onTap;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("子栏目")
    private List<ColumnDigestDTO> children;
}
