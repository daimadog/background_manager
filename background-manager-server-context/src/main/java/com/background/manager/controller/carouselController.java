package com.background.manager.controller;

import com.background.manager.model.dto.request.carousel.*;
import com.background.manager.model.dto.response.carousel.CarouselDTO;
import com.background.manager.model.dto.response.carousel.PortalStyleDTO;
import com.background.manager.response.ApiResponse;
import com.background.manager.service.BackgroundCarouselPageService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/carousel")
@Api(tags = "轮播页管理模块")
public class carouselController {

    @Resource
    private BackgroundCarouselPageService backgroundCarouselPageService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询轮播页",notes = "分页查询轮播页接口")
    public ApiResponse<IPage<CarouselDTO>> pageQuery(PageQueryCarouselRequest request){
        return ApiResponse.ok(backgroundCarouselPageService.pageQuery(request));
    }

    @PutMapping("/add")
    @ApiOperation(value = "新增轮播页",notes = "新增轮播页接口")
    public ApiResponse<String> add(@RequestBody AddCarouselRequest request){
        return ApiResponse.ok(backgroundCarouselPageService.add(request));
    }

    @PutMapping("/modify")
    @ApiOperation(value = "编辑轮播页",notes ="编辑轮播页接口")
    public  ApiResponse<Void> modify(@RequestBody ModifyCarouselRequest request){
        backgroundCarouselPageService.modify(request);
        return ApiResponse.ok();
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除轮播页",notes = "删除轮播页接口")
    public ApiResponse<Void> delete(@PathVariable("id") Long id) {
        backgroundCarouselPageService.delete(id);
        return ApiResponse.ok();
    }

    @ApiOperation(value = "门户动态风格配置列表",notes = "门户动态风格配置列表接口")
    @PutMapping("/listStyle")
    public ApiResponse<List<PortalStyleDTO>> listStyle(){
        return ApiResponse.ok(backgroundCarouselPageService.listStyle());
    }




}
