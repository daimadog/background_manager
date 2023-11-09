package com.background.manager.controller;

import com.background.manager.model.dto.request.receipt.*;
import com.background.manager.model.dto.response.receipt.ReceiptDTO;
import com.background.manager.model.dto.response.receipt.ReceiptDigestDTO;
import com.background.manager.response.ApiResponse;
import com.background.manager.service.BackgroundReceiptService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receipt")
@Api(tags = "发票管理模块")
@AllArgsConstructor
@DS("salve")
public class receiptController {

    private final BackgroundReceiptService backgroundReceiptService;

    @ApiOperation(value = "查询用户开票记录",notes = "查询用户开票记录接口")
    @GetMapping("/operation/pageQuery")
    public ApiResponse<IPage<ReceiptDigestDTO>> pageQuery(PageQueryReceiptRequest request){
        return ApiResponse.ok(backgroundReceiptService.pageQuery(request));
    }

    @ApiOperation(value = "审核发票",notes = "审核发票接口")
    @PostMapping("/operation/audit")
    public ApiResponse<Void> audit(@RequestBody AuditReceiptRequest request){
        backgroundReceiptService.audit(request);
        return ApiResponse.ok();
    }

    @ApiOperation(value = "查看发票详情",notes = "查看发票详情接口")
    @GetMapping("/getReceipt/{id}")
    public ApiResponse<ReceiptDTO> getReceipt(@ApiParam("发票编号") @PathVariable("id") Long id){
        return ApiResponse.ok(backgroundReceiptService.getReceipt(id));
    }


}
