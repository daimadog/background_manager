package com.background.manager.dto.request.zs;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("智算产品列表分页查询DTO")
public class ZsProductsSearchDTO  extends PageRequest {

    @ApiModelProperty(value = "产品名称")
    private String productNameLike;

    @ApiModelProperty(value = "排序字段")
    private String sortdatafield;

    @ApiModelProperty(value = "排序方法字符串(desc：倒序，asc：正序)")
    private String sortorder;

    @ApiModelProperty(value = "产品组件")
    private String serviceComponent;

    @ApiModelProperty(value = "产品类别id")
    private Long categoryId;

    @ApiModelProperty(value = "产品状态(using已上架，nousing待上架)")
    private String status;


}
