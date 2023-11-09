package com.background.manager.dto.request.zs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "智算用户列表请求")
public class ZsOrderListRequest extends CommonRequest{
    @ApiModelProperty(value = "申请单编号模糊查询")
    private String orderSnLike;
    @ApiModelProperty(value = "产品名称模糊查询")
    private String nameLike;
    @ApiModelProperty(value = "产品类别模糊查询")
    private String productNameLike;
    @ApiModelProperty(value = "类型搜索")
    private String typeName;
    @ApiModelProperty(value = "状态搜索")
    private String status;
}
