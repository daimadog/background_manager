package com.background.manager.controller;

import com.background.manager.model.dto.request.style.ListInfrastructureRequest;
import com.background.manager.model.dto.request.style.ListQueryBackgroundStyleRequest;
import com.background.manager.model.dto.request.style.ModifyBackgroundStyleRequest;
import com.background.manager.model.dto.request.style.ModifyInfrastructureRequest;
import com.background.manager.model.dto.response.style.BackgroundInfrastructureDTO;
import com.background.manager.model.dto.response.style.BackgroundStyleDTO;
import com.background.manager.response.ApiResponse;
import com.background.manager.service.BackgroundInfrastructureService;
import com.background.manager.service.BackgroundStyleService;
import com.baomidou.dynamic.datasource.annotation.DS;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/style")
@Api(tags = "动态风格配置模块")
@AllArgsConstructor
@DS("salve")
public class styleController {

    private final BackgroundStyleService backgroundStyleService;

    private final BackgroundInfrastructureService backgroundInfrastructureService;

    @GetMapping("/listQuery")
    @ApiOperation(value = "列表查询样式风格",notes = "列表查询样式风格接口")
    public ApiResponse<List<BackgroundStyleDTO>> list( ListQueryBackgroundStyleRequest request){
        return  ApiResponse.ok(backgroundStyleService.listQuery(request));
    }

    @PostMapping("/modify")
    @ApiOperation(value = "修改样式风格",notes = "修改样式风格接口")
    public ApiResponse<Void> edit(@RequestBody ModifyBackgroundStyleRequest request){
        backgroundStyleService.modify(request);
        return ApiResponse.ok();
    }

    @GetMapping("/listInfrastructure")
    @ApiOperation(value = "列表查询基础设施",notes = "列表查询基础设施接口")
    public ApiResponse<List<BackgroundInfrastructureDTO>> listInfrastructure(ListInfrastructureRequest request){
        return ApiResponse.ok(backgroundInfrastructureService.listInfrastructure(request));
    }

    @PostMapping("/modifyInfrastructure")
    @ApiOperation(value = "编辑基础设施",notes = "编辑基础设施接口")
    public ApiResponse<Void> editInfrastructure(@RequestBody ModifyInfrastructureRequest request){
        backgroundInfrastructureService.modifyInfrastructure(request);
        return ApiResponse.ok();
    }


}
