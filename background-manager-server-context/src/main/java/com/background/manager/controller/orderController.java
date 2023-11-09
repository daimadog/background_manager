package com.background.manager.controller;

import com.background.manager.model.dto.request.order.AuditOrderRequest;
import com.background.manager.model.dto.request.order.pageQueryAiOrderRequest;
import com.background.manager.model.dto.request.order.pageQueryHpcOrderRequest;
import com.background.manager.model.dto.response.order.AiOrderApplicationRecordDTO;
import com.background.manager.model.dto.response.order.HpcOrderApplicationRecordDTO;
import com.background.manager.response.ApiResponse;
import com.background.manager.service.AiOrderApplicationRecordService;
import com.background.manager.service.HpcOrderApplicationRecordService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Api(tags = "订单管理模块")
@AllArgsConstructor
@DS("salve")
public class orderController {

    private final HpcOrderApplicationRecordService hpcOrderApplicationRecordService;

    private final AiOrderApplicationRecordService aiOrderApplicationRecordService;

    @ApiOperation(value = "审核Hpc订单申请记录",notes = "审核Hpc订单申请记录接口")
    @PostMapping("/auditHpcOrder")
    public ApiResponse<Void> auditHpcOrderApply(@RequestBody AuditOrderRequest request){
        hpcOrderApplicationRecordService.auditHpcOrderApply(request);
        return ApiResponse.ok();
    }

    @ApiOperation(value = "分页查询HPC订单申请记录",notes = "分页查询HPC订单申请记录接口")
    @GetMapping("/pageQueryHpc")
    public ApiResponse<IPage<HpcOrderApplicationRecordDTO>> pageQueryHpcApplication(pageQueryHpcOrderRequest request){
        return ApiResponse.ok(hpcOrderApplicationRecordService.pageQueryHpcApplication(request));
    }

    @ApiOperation(value = "审核Ai订单申请记录",notes = "审核Ai订单申请记录接口")
    @PostMapping("/auditAiOrder")
    public ApiResponse<Void> auditAiOrderApply(@RequestBody AuditOrderRequest request){
        aiOrderApplicationRecordService.auditAiOrderApply(request);
        return ApiResponse.ok();
    }

    @ApiOperation(value = "分页查询AI订单申请记录",notes = "分页查询AI订单申请记录")
    @GetMapping("/pageQueryAI")
    public ApiResponse<IPage<AiOrderApplicationRecordDTO>> pageQueryAiApplication(pageQueryAiOrderRequest request){
        return ApiResponse.ok(aiOrderApplicationRecordService.pageQueryAiApplication(request));
    }

}
