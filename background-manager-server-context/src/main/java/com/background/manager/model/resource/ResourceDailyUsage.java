package com.background.manager.model.resource;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@ApiModel("资源使用情况(日统计)")
public class ResourceDailyUsage {
    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("日期")
    private Date date;
    @ApiModelProperty("横坐标-时间（HH::mm）")
    private String time;
    @ApiModelProperty("类型 0-AI，1-HPC")
    private Integer type;
    @ApiModelProperty("资源数量")
    private Integer number;
    @ApiModelProperty("占用率%")
    private BigDecimal percentage;

}
