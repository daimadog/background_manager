package com.background.manager.controller;

import com.background.manager.model.dto.request.resource.*;
import com.background.manager.model.dto.response.resource.CustomerUsageDTO;
import com.background.manager.model.dto.response.resource.ResourceDailyUsageDTO;
import com.background.manager.model.dto.response.resource.ResourceDigestDTO;
import com.background.manager.model.dto.response.resource.WorkRunningUsageDTO;
import com.background.manager.response.ApiResponse;
import com.background.manager.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

/**
 * @Description:超算中心资源管理中心控制器
 * @Author: 杜黎明
 * @Date: 2022/12/07 13:55:42
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/resource")
@Api(tags = "超算中心资源管理模块")
public class resourceController {

    @Resource
    private ClusterResourceService clusterResourceService;
    @Resource
    private  ResourceDailyUsageService resourceDailyUsageService;
    @Resource
    private  WorkRunningUsageService workRunningUsageService;
    @Resource
    private  CustomerUsageService customerUsageService;
    @Resource
    private DeviceService deviceService;

    @ApiOperation(value="新增集群资源",notes="新增集群资源接口")
    @PutMapping("/addResource")
    public ApiResponse<String> add(@RequestBody AddResourceRequest request){
        return  ApiResponse.ok(clusterResourceService.add(request));
    }

    @ApiOperation(value="查询集群资源对象",notes="查询集群资源对象接口")
    @GetMapping("/findResource/{id}")
    public ApiResponse<ResourceDigestDTO> getResource(@ApiParam("集群资源编号") @PathVariable("id") Long id){
        return ApiResponse.ok(clusterResourceService.findResource(id));
    }

    @ApiOperation(value="列表查询集群资源",notes ="列表查询集群资源接口")
    @GetMapping("/listResource")
    public ApiResponse<List<ResourceDigestDTO>> pageQuery(ListResourceRequest request){
        return ApiResponse.ok(clusterResourceService.listQuery(request));
    }

    @ApiOperation(value = "编辑集群资源",notes = "编辑集群资源接口")
    @PostMapping("/modifyResource")
    public ApiResponse<Void> modifyResource(@RequestBody ModifyResourceRequest request){
        clusterResourceService.modify(request);
        return ApiResponse.ok();
    }

    @ApiOperation(value = "删除集群资源",notes = "删除集群资源接口")
    @DeleteMapping("/deleteResource/{id}")
    public ApiResponse<Void> deleteResource(@ApiParam("资源编号") @PathVariable("id") Long id){
        clusterResourceService.delete(id);
        return ApiResponse.ok();
    }

    @ApiOperation(value = "新增单日资源使用情况",notes = "新增单日资源使用情况接口")
    @PutMapping("/addDailyResource")
    public ApiResponse<Void> addDailyResource(@RequestBody AddResourceDailyUsage request){
        resourceDailyUsageService.add(request);
        return  ApiResponse.ok();
    }

    @ApiOperation(value="查找指定日期的单日资源使用情况",notes="查找指日期的单日资源使用情况接口")
    @GetMapping("/findResourceDailyUsage")
    public ApiResponse <ResourceDailyUsageDTO> getResourceDailyUsage(FindResourceDailyUsageRequest request){
        return ApiResponse.ok(resourceDailyUsageService.findResource(request));
    }

    @ApiOperation(value = "编辑资源单日使用情况",notes = "编辑资源单日使用情况接口")
    @PostMapping("/modifyResourceDailyUsage")
    public ApiResponse<Void> modifyResourceDailyUsage(@RequestBody ModifyResourceDailyUsageRequest request){
        resourceDailyUsageService.modifyResourceDailyUsage(request);
        return ApiResponse.ok();
    }

    @ApiOperation(value = "删除资源单日使用情况",notes = "删除资源单日使用情况接口")
    @DeleteMapping("/deleteResourceDailyUsage/{date}")
    public ApiResponse<Void> deleteResourceDailyUsage(@ApiParam("日期") @PathVariable("date") Date date){
        resourceDailyUsageService.deleteResourceDailyUsage(date);
        return ApiResponse.ok();
    }

    @ApiOperation(value = "新增运行节点",notes = "新增运行节点接口")
    @PutMapping("/addWorkRunningUsage")
    public ApiResponse<String> addWorkRunningUsage(@RequestBody AddWorkRunningUsageRequest request){
        return ApiResponse.ok(workRunningUsageService.add(request));
    }

    @ApiOperation(value = "查找运行节点",notes = "查找运行节点接口")
    @GetMapping("/getWorkRunningUsage/{id}")
    public ApiResponse<WorkRunningUsageDTO> getWorkRunningUsage(@ApiParam("运行节点编号") @PathVariable("id") Long id){
        return ApiResponse.ok(workRunningUsageService.getWorkRunningUsage(id));
    }

    @ApiOperation(value = "列表查询运行节点",notes = "列表查询运行节点接口")
    @GetMapping("/listWorkRunningUsage")
    public ApiResponse<List<WorkRunningUsageDTO>> list(){
        return ApiResponse.ok(workRunningUsageService.listWorkRunningUsage());
    }

    @ApiOperation(value = "编辑运行节点",notes = "编辑运行节点接口")
    @PostMapping("/modifyWorkRunningUsage")
    public ApiResponse<Void> modifyWorkRunningUsage(@RequestBody ModifyWorkRunningUsageRequest request){
        workRunningUsageService.modifyWorkRunningUsage(request);
        return ApiResponse.ok();
    }

    @ApiOperation(value = "删除运行节点",notes ="删除运行节点接口")
    public ApiResponse<Void> deleteWorkRunningUsage(@ApiParam("节点编号") @PathVariable Long id){
        workRunningUsageService.deleteWorkRunningUsage(id);
        return  ApiResponse.ok();
    }

    @ApiOperation(value = "新增客户情况",notes = "新增客户情况接口")
    @PutMapping("/addCustomerUsage")
    public ApiResponse<Void> addCustomerUsage (@RequestBody AddCustomerUsageRequest request){
        customerUsageService.addCustomerUsage(request);
        return ApiResponse.ok();
    }

    @ApiOperation(value = "编辑客户情况",notes = "编辑客户情况接口")
    @PostMapping("/modifyCustomerUsage")
    public ApiResponse<Void> modifyCustomerUsage(@RequestBody ModifyCustomerUsageRequest request){
        customerUsageService.modifyCustomerUsage(request);
        return ApiResponse.ok();
    }

    @ApiOperation(value="列表查询客户情况",notes ="列表查询客户情况")
    @GetMapping("/listCustomerUsage/{type}")
    public ApiResponse<List<CustomerUsageDTO>> listCustomerUsage(@ApiParam("客户种类(0-类型 1-行业)") @PathVariable("type") Integer type){
        return ApiResponse.ok(customerUsageService.listCustomerUsage(type));
    }

    @ApiOperation(value = "删除客户情况",notes = "删除客户情况接口")
    @DeleteMapping("/deleteCustomerUsage/{id}")
    public ApiResponse<Void> deleteCustomerUsage(@ApiParam("客户编号") @PathVariable("id") Long id){
        customerUsageService.deleteCustomerUsage(id);
        return ApiResponse.ok();
    }

    @ApiOperation(value = "获取轻应用播放token",notes = "获取轻应用播放token接口")
    @PostMapping("/getKitToken")
    public ApiResponse<String> getKitToken(){
        return ApiResponse.ok(deviceService.getKitToken());
    }
}
