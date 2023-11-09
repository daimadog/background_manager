package com.background.manager.dto.request.zs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("智算获取订单管理列表")
public class orderListRequest  extends  PageRequest{

    @ApiModelProperty("申请订单编号模糊查询")
    private String orderSnLike;

    @ApiModelProperty("名称模糊查询")
    private String nameLike;

    @ApiModelProperty("组织名称模糊查询")
    private String orgNameLke;

    @ApiModelProperty("产品类别模糊查询")
    private String productNameLike;

    @ApiModelProperty("类型（apply:订购，release:：退订，renew：续订）")
    private String typeName;

    @ApiModelProperty("状态名称（approving：审批中，completed：开通完成，lev2_refused：开通失败，" +
                         "release_pending：退订中，release_lev2_refused：退订失败，" +
                         "renew_pending：续订中，renew_lev2_refused：续订失败，" +
                         "renew_success：续订完成，pending：开通中，" +
                          "failure：处理失败，release_success：退订完成）")
    private String statusName;
}
