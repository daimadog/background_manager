package com.background.manager.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel("基于持久对象")
public class BasePO implements Serializable {

    @ApiModelProperty("创建者")
    @TableField(
            fill = FieldFill.INSERT
    )
    private String creator;

    @TableField(
            fill = FieldFill.INSERT
    )
    @ApiModelProperty("创建者ip")
    private String creatorIp;


    @ApiModelProperty("创建时间")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @TableField(
            fill = FieldFill.INSERT
    )
    private LocalDateTime createTime;

    @TableField(
            fill = FieldFill.INSERT_UPDATE
    )
    @ApiModelProperty("更新人")
    private String modifier;

    @TableField(
            fill = FieldFill.INSERT_UPDATE
    )
    @ApiModelProperty("更新人ip")
    private String modifierIp;

    @TableField(
            fill = FieldFill.INSERT_UPDATE
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @ApiModelProperty("更新时间")
    private LocalDateTime modifyTime;

}
