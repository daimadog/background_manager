package com.background.manager.model.dto.request.home;

import com.background.manager.constants.database.BasePageDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("分页查询典型案例请求体")
public class PageQueryTypicalClassRequest extends BasePageDTO {

}
