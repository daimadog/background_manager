package com.background.manager.model.resource;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;

@Data
@ApiModel("客户情况")
public class CustomerUsage {

    @ApiModelProperty("主键ID")
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("客户种类 0-类型，1-行业")
    private Integer type;
    @ApiModelProperty("客户名称")
    private String name;
    @ApiModelProperty("客户数量")
    private Integer value;
    @ApiModelProperty("客户占比")
    private BigDecimal proportion;

}
