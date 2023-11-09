package com.background.manager.dto.request.zs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("智算获取指定客户的可用资源请求体")
public class getAvailableResourceRequest  extends PageRequest{

    @ApiModelProperty("用户id")
    private String userId;


}
