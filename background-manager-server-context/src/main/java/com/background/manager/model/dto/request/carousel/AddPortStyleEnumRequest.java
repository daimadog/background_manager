package com.background.manager.model.dto.request.carousel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("新增动态风格请求体")
public class AddPortStyleEnumRequest {

    private Integer number;

    private String description;

}
