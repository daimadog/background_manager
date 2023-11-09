package com.background.manager.controller;

import com.background.manager.model.dto.request.seo.AddSeoRuleRequest;
import com.background.manager.model.dto.request.seo.ModifySeoRuleRequest;
import com.background.manager.model.dto.request.seo.PageQuerySeoRuleRequest;
import com.background.manager.model.dto.response.seo.BackgroundSeoRuleDTO;
import com.background.manager.response.ApiResponse;
import com.background.manager.service.BackgroundSeoRuleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController()
@Api(tags = "seo配置模块")
@RequestMapping("/seo")
@AllArgsConstructor
public class seoRuleController {

    private  final BackgroundSeoRuleService backgroundSeoRuleService;

    @ApiOperation(value = "新增seo配置",notes = "新增seo配置接口")
    @PutMapping("/save")
    public ApiResponse<Void> add(@RequestBody AddSeoRuleRequest request){
        backgroundSeoRuleService.add(request);
        return ApiResponse.ok();
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除seo配置",notes = "删除seo配置接口")
    public ApiResponse<Void> delete(@ApiParam("规则编号") @PathVariable("id") Long id){
        backgroundSeoRuleService.delete(id);
        return ApiResponse.ok();
    }

    @GetMapping("/pageQuery")
    @ApiOperation(value = "分页查询seo配置",notes = "分页查询seo配置接口")
    public ApiResponse<IPage<BackgroundSeoRuleDTO>> listRule(PageQuerySeoRuleRequest request){
        return ApiResponse.ok(backgroundSeoRuleService.pageQuery(request));
    }

    @GetMapping("/listQuery")
    @ApiOperation(value = "门户列表查询seo配置",notes = "门户列表查询seo配置接口")
    public ApiResponse<List<BackgroundSeoRuleDTO>> list(){
        return ApiResponse.ok(backgroundSeoRuleService.listQuery());
    }

    @ApiOperation("修改seo配置")
    @PostMapping("/modifySeo")
    public ApiResponse<Void> edit(@RequestBody ModifySeoRuleRequest request){
        backgroundSeoRuleService.modify(request);
        return ApiResponse.ok();
    }


}
