package com.background.manager.controller;

import com.background.manager.model.dto.request.workOrder.PageQueryBackgroundWorkOrderRequest;
import com.background.manager.model.dto.request.workOrder.ReplyWorkOrderMessageRequest;
import com.background.manager.model.dto.response.workOrder.BackgroundWorkOrderDTO;
import com.background.manager.model.dto.response.workOrder.BackgroundWorkOrderDigestDTO;
import com.background.manager.response.ApiResponse;
import com.background.manager.service.BackgroundWorkOrderMessageService;
import com.background.manager.service.BackgroundWorkOrderService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wordOrder")
@Api(tags = "工单管理模块")
@AllArgsConstructor
@DS("salve")
public class workOrderController {

    private final BackgroundWorkOrderService backgroundWorkOrderService;

    private final BackgroundWorkOrderMessageService backgroundWorkOrderMessageService;

    @GetMapping("/pageQuery")
    @ApiOperation(value = "工单管理列表",notes = "工单管理列表接口")
    public ApiResponse<IPage<BackgroundWorkOrderDigestDTO>> pageQuery(PageQueryBackgroundWorkOrderRequest request){
        return ApiResponse.ok(backgroundWorkOrderService.pageQuery(request));
    }

    @PostMapping("/close/{id}")
    @ApiOperation(value = "关闭工单",notes = "关闭工单接口")
    public ApiResponse<Void> closeWorkOrder (@ApiParam("工单编号") @PathVariable("id") String id){
        backgroundWorkOrderService.closeWorkOrder(id);
        return ApiResponse.ok();
    }

    @PostMapping("/reply")
    @ApiOperation(value = "回复工单",notes = "回复工单接口")
    public ApiResponse<Void> reply(@RequestBody ReplyWorkOrderMessageRequest request){
        backgroundWorkOrderMessageService.replyMessage(request);
        return ApiResponse.ok();
    }

    @GetMapping("/findWorkOrder/{id}")
    @ApiOperation(value = "工单详情",notes = "工单详情接口")
    public ApiResponse<BackgroundWorkOrderDTO> detail(@ApiParam("工单编号") @PathVariable("id") String id){
        return ApiResponse.ok(backgroundWorkOrderService.findWorkOrder(id));
    }

}
