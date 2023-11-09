package com.background.manager.model.dto.response.home;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("典型案例DTO")
public class TypicalClassDigestDTO {

    @ApiModelProperty("主键ID")
    private Long id;
    @ApiModelProperty("主标题")
    private String title;


}
