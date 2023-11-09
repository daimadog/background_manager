package com.background.manager.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.background.manager.dto.request.console.*;
import com.background.manager.dto.request.zs.*;
import com.background.manager.dto.response.console.CustomerListDTO;
import com.background.manager.response.ApiResponse;
import com.background.manager.service.ConsoleService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

@RestController
@Api(tags = "智算超算总览页接口管理")
@RequestMapping("/console")
@DS("salve")
public class ConsoleController {

    @Resource
    private ConsoleService consoleService;

    @ApiOperation(value = "超算管理员查看普通用户列表")
    @PostMapping(value = "/csCommonUserList")
    public ApiResponse<JSONObject> csCommonUserList(@RequestBody csCommonUserListRequest request){
        try {
            return ApiResponse.ok(consoleService.csCommonUserList(request));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @ApiOperation(value = "超算资源上架列表")
    @PostMapping(value = "/queryResourceDisplayList")
    public ApiResponse<JSONObject> queryResourceDisplayList(@RequestBody queryResourceDisplayListRequest request){
            return ApiResponse.ok(consoleService.queryResourceDisplayList(request));
    }

    @ApiOperation(value = "超算查询可以上架的资源")
    @GetMapping(value = "/queryActiveResources")
    public ApiResponse<JSONArray> queryActiveResources(){
        return ApiResponse.ok(consoleService.queryActiveResources());
    }

    @ApiOperation(value = "超算下架资源")
    @PostMapping(value = "/updateResourceDisplayStatus")
    public ApiResponse<Boolean> updateResourceDisplayStatus(updateResourceDisplayStatusRequest request){
        return ApiResponse.ok(consoleService.updateResourceDisplayStatus(request));
    }

    @ApiOperation(value = "超算上架资源")
    @PostMapping(value = "/updateResourceDisplay")
    public ApiResponse<Boolean> updateResourceDisplay(updateResourceDisplayRequest request){
        return ApiResponse.ok(consoleService.updateResourceDisplay(request));
    }

    @ApiOperation(value = "超算获取资源目录")
    @GetMapping(value = "/getItem")
    public ApiResponse<JSONObject> getItem(getItemRequest request){
        return ApiResponse.ok(consoleService.getItem(request));
    }

    @ApiOperation(value = "超算获取计费规则")
    @PostMapping(value = "/getChargeRules")
    public ApiResponse<JSONArray> getChargeRules(@RequestBody getChargeRulesRequest request){
        return ApiResponse.ok(consoleService.getChargeRules(request));
    }

    @ApiOperation(value = "超算获取产品月销售额数据",notes = "用于管理员获取产品月销售额数据")
    @PostMapping(value = "/getProduct")
    public ApiResponse<JSONArray> getProduct(@RequestBody getProductRequest request) throws UnsupportedEncodingException {
        return ApiResponse.ok(consoleService.getProduct(request));
    }

    @ApiOperation(value = "超算获取前10用户购买金额总额",notes = "用于获取前十用户购买金额总额")
    @PostMapping(value = "/getCustomer")
    public ApiResponse<JSONArray> getCustomer(@RequestBody getCustomerRequest request) throws UnsupportedEncodingException {
        return ApiResponse.ok(consoleService.getCustomer(request));
    }

    @ApiOperation(value = "超算获取所有用户的订单列表")
    @PostMapping(value = "/csOrderList")
    public ApiResponse<JSONObject> csOrderList(@RequestBody CsOrderListRequest request) throws UnsupportedEncodingException {
        return ApiResponse.ok(consoleService.csOrderList(request));
    }

    @ApiOperation(value = "超算获取所有用户的账单列表")
    @PostMapping(value = "/csBillList")
    public ApiResponse<JSONObject> csBillList(@RequestBody CsBillListRequest request) throws UnsupportedEncodingException {
        return ApiResponse.ok(consoleService.csBillList(request));
    }

    @ApiOperation(value = "超算获取所有用户的收支明细列表")
    @PostMapping(value = "/csTradeList")
    public ApiResponse<JSONObject> csTradeList(@RequestBody CsTradeListRequest request) throws UnsupportedEncodingException {
        return ApiResponse.ok(consoleService.csTradeList(request));
    }

    @ApiOperation(value = "超算实例管理列表")
    @PostMapping(value = "/getCustomerList")
    public ApiResponse<IPage<CustomerListDTO>> getCustomerList(@RequestBody getCustomerListRequest request){
        return ApiResponse.ok(consoleService.getCustomerList(request));
    }

    @ApiOperation(value = "超算获取指定客户收支明细列表")
    @PostMapping(value = "/getCustomerTradeList")
    public ApiResponse<JSONObject> getCustomerTradeList(@RequestBody getCustomerTradeListRequest request) throws UnsupportedEncodingException {
        return ApiResponse.ok(consoleService.getCustomerTradeList(request));
    }

    @ApiOperation(value = "超算获取指定用户资源信息")
    @GetMapping(value = "/csResourceList/{userId}")
    public ApiResponse<JSONArray> csResourceList(@PathVariable String userId){
        return ApiResponse.ok(consoleService.csResourceList(userId));
    }

    @ApiOperation(value = "智算获取产品列表")
    @GetMapping(value = "/zsProductsList")
    public ApiResponse<JSONObject> zsProductsList(ZsProductsSearchDTO zsProductsSearchDTO){
        return ApiResponse.ok(consoleService.zsProductsList(zsProductsSearchDTO));
    }

    @ApiOperation(value = "智算更新产品状态")
    @PutMapping("/zsUpdateProductStatus")
    public ApiResponse<Boolean> zsUpdateProductStatus(@RequestBody zsUpdateProductStatusRequest request){
        return ApiResponse.ok(consoleService.zsUpdateProductStatus(request));
    }

    @ApiOperation(value = "智算实例管理")
    @GetMapping("/product/resource")
    public ApiResponse<JSONObject> resourceStrategy(resourceStrategyRequest request){
        return ApiResponse.ok(consoleService.resourceStrategy(request));
    }

    @ApiOperation(value = "智算获取计费策略")
    @GetMapping(value = "/billing/strategy")
    public ApiResponse<JSONObject> billingStrategyList(billingStrategyRequest request){
        return ApiResponse.ok(consoleService.billingStrategyList(request));
    }

    @ApiOperation(value = "智算计费策略下拉规格族列表")
    @GetMapping("/billing/spec_group")
    public ApiResponse<JSONArray> billingStrategySpecGroup(@ApiParam("策略id") @RequestParam("strategyId") Long strategyId){
        return ApiResponse.ok(consoleService.billingStrategySpecGroup(strategyId));
    }

    @ApiOperation(value = "智算更新计费策略")
    @PutMapping("/updateBillingStrategy")
    public ApiResponse<Boolean>  updateBillingStrategy(@RequestBody updateBillingStrategyRequest request){
        return ApiResponse.ok(consoleService.updateBillingStrategy(request));
    }

    @ApiOperation(value = "智算获取客户资源列表")
    @GetMapping("/products/resources")
    public ApiResponse<JSONObject> productResourcesList(productResourcesListRequest request){
        return ApiResponse.ok(consoleService.productResourcesList(request));
    }

    @ApiOperation(value = "智算获取客户指定可用资源列表")
    @GetMapping("/account/resources")
    public ApiResponse<JSONObject> getAvailableResource(getAvailableResourceRequest request){
        return ApiResponse.ok(consoleService.getAvailableResource(request));
    }

    @ApiOperation(value = "智算获取客户指定收支明细")
    @GetMapping("/accountDeals")
    public ApiResponse<JSONObject>  accountDeals(accountDealsRequest request){
            return ApiResponse.ok(consoleService.accountDeals(request));
    }

    @ApiOperation(value = "智算获取产品月销售数据")
    @GetMapping("/operation/statistics/order_type")
    public ApiResponse<JSONObject> getOrderStatistics(orderStatisticsRequest request) throws UnsupportedEncodingException {
        return ApiResponse.ok(consoleService.getOperationStatistics(request));
    }

    @ApiOperation(value = "智算获取用户购买数据")
    @GetMapping("/operation/statistics/order_account")
    public ApiResponse<JSONObject> getAccountStatistics(accountStatisticsRequest request) throws UnsupportedEncodingException {
        return ApiResponse.ok(consoleService.getAccountStatistics(request));
    }

    @ApiOperation(value = "智算获取订单管理列表")
    @GetMapping("/audit/approval/order")
    public ApiResponse<JSONObject> orderList(orderListRequest request){
        return ApiResponse.ok(consoleService.orderList(request));
    }

    @ApiOperation(value = "智算获取客户账单")
    @GetMapping("/bills/listCycleBills")
    public ApiResponse<JSONObject> listCycleBills(listCycleBillsRequest request){
        return ApiResponse.ok(consoleService.listCycleBills(request));
    }

    @ApiOperation(value = "智算获取收支明细")
    @GetMapping("/bss/account_deals")
    public ApiResponse<JSONObject> accountDealsList(accountDealsListRequest request){
        return ApiResponse.ok(consoleService.accountDealsList(request));
    }

}