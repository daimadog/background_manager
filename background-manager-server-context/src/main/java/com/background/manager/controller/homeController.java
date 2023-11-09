package com.background.manager.controller;

import com.background.manager.model.dto.request.home.*;
import com.background.manager.model.dto.response.home.PartnersDTO;
import com.background.manager.model.dto.response.home.TypicalClassDTO;
import com.background.manager.model.dto.response.home.TypicalClassDigestDTO;
import com.background.manager.response.ApiResponse;
import com.background.manager.service.BackgroundPartnersService;
import com.background.manager.service.BackgroundTypicalClassService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/home")
@Api(tags ="首页管理模块")
public class homeController {

    @Resource
    private BackgroundTypicalClassService backgroundTypicalClassService;

    @Resource
    private BackgroundPartnersService backgroundPartnersService;

    @GetMapping("/pageQuery")
    @ApiOperation(value = "分页查询典型案例",notes = "分页查询典型案例接口")
    public ApiResponse<IPage<TypicalClassDigestDTO>> pageQuery( PageQueryTypicalClassRequest request) {
        return ApiResponse.ok(backgroundTypicalClassService.pageQuery(request));
    }

    @PostMapping("/addTypicalClass")
    @ApiOperation(value = "新增典型案例",notes="新增典型案例接口")
    public ApiResponse<String> addTypicalClass(@RequestBody  AddTypicalClassRequest request) {
        return ApiResponse.ok(backgroundTypicalClassService.addTypicalClass(request));
    }

    @GetMapping("/getTypicalClass/{id}")
    @ApiOperation(value = "查询典型案例",notes="查询典型案例接口")
    public ApiResponse<TypicalClassDTO> getTypicalClass(@ApiParam("案例编号") @PathVariable("id") Long id) {
        return ApiResponse.ok(backgroundTypicalClassService.getTypicalClass(id));
    }

    @PutMapping("/modify")
    @ApiOperation(value="编辑典型案例",notes = "编辑典型案例接口")
    public ApiResponse<Void> modifyTypicalClass(@RequestBody ModifyTypicalClassRequest request) {
        backgroundTypicalClassService.modify(request);
        return ApiResponse.ok();
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value="删除典型案例",notes = "删除典型案例接口")
    public ApiResponse<Void> delete(@ApiParam("案例编号") @PathVariable("id") Long id) {
        backgroundTypicalClassService.delete(id);
        return ApiResponse.ok();
    }

    @PostMapping("/addPartners")
    @ApiOperation(value = "新增合作伙伴",notes = "新增合作伙伴接口")
    public ApiResponse<String> addPartners(@RequestBody AddPartnersRequest request) {
        return ApiResponse.ok(backgroundPartnersService.addPartners(request));
    }

    @GetMapping("/list")
    @ApiOperation(value = "列表查询合作伙伴",notes = "列表查询合作伙伴接口")
    public ApiResponse<List<PartnersDTO>> list() {
        return ApiResponse.ok(backgroundPartnersService.listPartners());
    }

    @ApiOperation(value = "修改合作伙伴",notes = "修改合作伙伴接口")
    @PutMapping("/modifyPartners")
    public ApiResponse<Void> modifyPartners(@RequestBody ModifyPartnersRequest request){
        backgroundPartnersService.modifyPartners(request);
        return ApiResponse.ok();
    }

    @DeleteMapping("/deletePartners/{id}")
    @ApiOperation(value = "删除合作伙伴",notes = "删除合作伙伴接口")
    public ApiResponse<Void> deletePartners(@ApiParam("合作伙伴编号") @PathVariable("id") Long id){
        backgroundPartnersService.deletePartners(id);
        return ApiResponse.ok();
    }

}
