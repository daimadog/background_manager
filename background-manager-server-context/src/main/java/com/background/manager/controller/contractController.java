package com.background.manager.controller;

import com.background.manager.model.dto.request.contract.*;
import com.background.manager.model.dto.response.contract.BackgroundContractDTO;
import com.background.manager.model.dto.response.contract.BackgroundContractDigestDTO;
import com.background.manager.model.dto.response.contract.ReChargeContractDTO;
import com.background.manager.response.ApiResponse;
import com.background.manager.service.BackgroundContractService;
import com.background.manager.service.ContractApplicationRecordService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/contract")
@Api(tags = "合同管理模块")
@AllArgsConstructor
@DS("salve")
public class contractController {

    private final BackgroundContractService backgroundContractService;

    private final ContractApplicationRecordService contractApplicationRecordService;

    @PutMapping("/operation/create")
    @ApiOperation(value = "创建合同",notes = "创建合同接口")
    public ApiResponse<String> createContract(@Valid  @RequestBody CreateContractRequest request){
        return ApiResponse.ok(backgroundContractService.create(request));
    }

    @GetMapping("/operation/page")
    @ApiOperation(value = "分页查询合同列表",notes = "分页查询合同列表接口")
    public ApiResponse<IPage<BackgroundContractDigestDTO>> pageQuery(PageQueryContractRequest request){
        return ApiResponse.ok(backgroundContractService.pageQuery(request));
    }

    @PostMapping("/operation/edit")
    @ApiOperation(value = "编辑合同",notes = "编辑合同接口")
    public ApiResponse<Void> edit(@RequestBody ModifyContractRequest request){
        backgroundContractService.edit(request);
        return ApiResponse.ok();
    }

    @PostMapping("/operation/audit")
    @ApiOperation(value = "审核合同",notes = "审核合同接口")
    public ApiResponse<Void> audit(@RequestBody AuditContactRequest request){
        backgroundContractService.audit(request);
        return ApiResponse.ok();
    }

    @DeleteMapping("/operation/delete/{id}")
    @ApiOperation(value = "删除合同",notes = "删除合同接口")
    public ApiResponse<Void> delete(@ApiParam("合同编号ID") @PathVariable("id") Long id){
        backgroundContractService.delete(id);
        return ApiResponse.ok();
    }

    @GetMapping("/find/{id}")
    @ApiOperation(value = "查看合同详情",notes = "查看合同详情接口")
    public ApiResponse<BackgroundContractDTO> findContract(@ApiParam("合同编号ID") @PathVariable("id") Long id){
        return ApiResponse.ok(backgroundContractService.getContract(id));
    }

    @ApiOperation(value = "审核合同充值")
    @PostMapping("/applyRechargeContract")
    public ApiResponse<Void> applyRechargeContract(@RequestBody ApplyRechargeContractRequest request){
        contractApplicationRecordService.apply(request);
        return ApiResponse.ok();
    }

    @ApiOperation(value = "分页查询合同充值列表")
    @GetMapping("/operation/pageQueryReChargeContract")
    public ApiResponse<IPage<ReChargeContractDTO>> listReChargeContractDTO(PageQueryReChargeContractRequest request){
        return ApiResponse.ok(contractApplicationRecordService.PageQueryReChargeContract(request));
    }

}