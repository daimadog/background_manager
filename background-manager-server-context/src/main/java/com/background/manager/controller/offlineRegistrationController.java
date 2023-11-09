package com.background.manager.controller;

import com.background.manager.model.dto.request.offlineRegistration.AddRegistrationRequest;
import com.background.manager.model.dto.request.offlineRegistration.PageQueryOfflineMessageRequest;
import com.background.manager.model.dto.response.offlineRegistration.OfflineMessageDTO;
import com.background.manager.model.dto.response.offlineRegistration.OfflineMessageDigestDTO;
import com.background.manager.exception.enums.ResultCodeEnum;
import com.background.manager.response.ApiResponse;
import com.background.manager.service.BackgroundOfflineMessageService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @Description: 离线登记控制器
 * @Author: 杜黎明
 * @Date: 2022/11/03 09:06:23
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/offline")
@Api(tags = "离线登记管理模块")
public class offlineRegistrationController {

    @Resource
    private BackgroundOfflineMessageService backgroundOfflineMessageService;

    @PutMapping("/add")
    @ApiOperation(value = "提交离线登记信息",notes = "提交离线登记信息接口")
    public ApiResponse<Void> add(@RequestBody AddRegistrationRequest request){
        backgroundOfflineMessageService.add(request);
        return ApiResponse.ok();
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页条件查询离线登记内容",notes = "分页条件查询离线登记内容接口")
    public ApiResponse<IPage<OfflineMessageDigestDTO>> pageQuery(PageQueryOfflineMessageRequest request){
        return ApiResponse.ok(backgroundOfflineMessageService.pageQuery(request));
    }

    @GetMapping("/getOfflineMessage/{id}")
    @ApiOperation(value = "查看离线登记详情",notes = "查看离线登记详情接口")
    public ApiResponse<OfflineMessageDTO> getOfflineMessage(@ApiParam("离线登记id编号") @PathVariable("id") Long id){
        return ApiResponse.ok(backgroundOfflineMessageService.getOfflineMessage(id));
    }

    @PostMapping("/process/{id}")
    @ApiOperation(value = "处理离线登记内容",notes = "处理离线登记内容接口")
    public ApiResponse<Void> process(@ApiParam("离线登记id编号") @PathVariable("id") Long id){
        if (backgroundOfflineMessageService.process(id)){
            return ApiResponse.ok();
        }else {
            return ApiResponse.error(ResultCodeEnum.PROCESS_FAIL);
        }
    }

}
