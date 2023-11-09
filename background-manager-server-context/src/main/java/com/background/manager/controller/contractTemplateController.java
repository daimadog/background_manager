package com.background.manager.controller;

import com.background.manager.model.dto.request.template.CreateTemplateRequest;
import com.background.manager.model.dto.request.template.ModifyContractTemplateRequest;
import com.background.manager.model.dto.request.template.listQueryContractTemplateRequest;
import com.background.manager.model.dto.response.template.ContractTemplateDTO;
import com.background.manager.response.ApiResponse;
import com.background.manager.service.BackgroundContractTemplateService;
import com.baomidou.dynamic.datasource.annotation.DS;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/contract/template")
@Api(tags = "合同模板管理模块")
@AllArgsConstructor
@DS("salve")
public class contractTemplateController {

    private final BackgroundContractTemplateService backgroundContractTemplateService;

    @ApiOperation(value = "上传合同模板",notes = "上传合同模板接口")
    @PutMapping("/add")
    public ApiResponse<String> create(@RequestBody CreateTemplateRequest request){
        return ApiResponse.ok(backgroundContractTemplateService.createContractTemplate(request));
    }

    @ApiOperation(value = "列表查询合同模板",notes = "列表查询合同模板接口")
    @GetMapping("/list")
    public ApiResponse<List<ContractTemplateDTO>> list (listQueryContractTemplateRequest request){
        return ApiResponse.ok(backgroundContractTemplateService.listQuery(request));
    }

    @ApiOperation(value = "修改合同模板状态",notes = "修改合同模板状态接口")
    @PostMapping("/process/{id}")
    public ApiResponse<Void> process(@ApiParam("合同模板编号") @PathVariable("id") Long id){
        backgroundContractTemplateService.process(id);
        return ApiResponse.ok();
    }

    @ApiOperation(value = "修改合同模板",notes = "修改合同模板接口")
    @PostMapping("/modify")
    public ApiResponse<Void> edit(@RequestBody ModifyContractTemplateRequest request){
        backgroundContractTemplateService.modify(request);
        return ApiResponse.ok();
    }

    @ApiOperation(value = "删除合同模板",notes = "删除合同模板接口")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> delete(@ApiParam("合同模板编号") @PathVariable("id") Long id){
        backgroundContractTemplateService.delete(id);
        return ApiResponse.ok();
    }

}
