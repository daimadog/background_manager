package com.background.manager.model.dto.request.offlineRegistration;

import com.background.manager.constants.database.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@ApiModel("分页查询离线登记内容请求体")
public class PageQueryOfflineMessageRequest extends BasePageDTO {

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("公司名称/学校名称")
    private String company;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("处理状态")
    private Integer  processState;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

}
