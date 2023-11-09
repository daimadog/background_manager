package com.background.manager.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.background.manager.dto.request.console.*;
import com.background.manager.dto.request.zs.*;
import com.background.manager.dto.response.console.CustomerListDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.io.UnsupportedEncodingException;

/**
 * @Description: 智算超算总览页接口
 * @Author: 杜黎明
 * @Date: 2023/03/17 10:14:59
 * @Version: 1.0.0
 */
public interface ConsoleService {

    /**
     * Description: 超算管理员查看普通用户列表
     * @param request 请求
     * @return {@link JSONObject }
     * @author 杜黎明
     * @date 2023/03/17 10:16:06
     */
    JSONObject csCommonUserList(csCommonUserListRequest request) throws UnsupportedEncodingException;

    /**
     * Description:资源上架列表
     * @param request 请求
     * @return {@link JSONObject }
     * @author 杜黎明
     * @date 2023/03/17 15:54:16
     */
    JSONObject queryResourceDisplayList(queryResourceDisplayListRequest request);

    /**
     * Description:查询可以上架的资源
     * @return {@link JSONObject }
     * @author 杜黎明
     * @date 2023/03/17 15:58:28
     */
    JSONArray queryActiveResources();

    /**
     * Description: 下架资源
     * @param request 请求
     * @return {@link JSONObject }
     * @author 杜黎明
     * @date 2023/03/17 16:04:25
     */
    Boolean updateResourceDisplayStatus(updateResourceDisplayStatusRequest request);

    /**
     * Description: 上架资源
     * @param request 请求
     * @return {@link JSONObject }
     * @author 杜黎明
     * @date 2023/03/17 16:08:35
     */
    Boolean updateResourceDisplay(updateResourceDisplayRequest request);

    /**
     * Description: 获取资源目录
     * @param request 请求
     * @return {@link JSONObject }
     * @author 杜黎明
     * @date 2023/03/17 16:15:35
     */
    JSONObject getItem(getItemRequest request);

    /**
     * Description: 获取计费规则
     * @param request 请求
     * @return {@link JSONObject }
     * @author 杜黎明
     * @date 2023/03/17 16:21:13
     */
    JSONArray getChargeRules(getChargeRulesRequest request);

    /**
     * Description: 管理员获取产品月销售额数据
     * @param request 请求
     * @return {@link JSONObject }
     * @author 杜黎明
     * @date 2023/03/24 10:27:07
     */
    JSONArray getProduct(getProductRequest request) throws UnsupportedEncodingException;

    /**
     * Description:获取前10用户购买金额总额
     * @param request 请求
     * @return {@link JSONObject }
     * @author 杜黎明
     * @date 2023/03/24 10:32:43
     */
    JSONArray getCustomer(getCustomerRequest request) throws UnsupportedEncodingException;

    /**
     * Description: 获取用户订单列表
     * @param request 请求
     * @return {@link JSONObject }
     * @author 杜黎明
     * @date 2023/03/31 13:53:01
     */
    JSONObject csOrderList(CsOrderListRequest request) throws UnsupportedEncodingException;

    /**
     * Description: 超算获取用户账单列表
     * @param request 请求
     * @return {@link JSONObject }
     * @author 杜黎明
     * @date 2023/03/31 14:08:55
     */
    JSONObject csBillList(CsBillListRequest request) throws UnsupportedEncodingException;

    /**
     * Description: 超算获取所有用户收支明细
     * @param request 请求
     * @return {@link JSONObject }
     * @author 杜黎明
     * @date 2023/03/31 14:16:38
     */
    JSONObject csTradeList(CsTradeListRequest request) throws UnsupportedEncodingException;

    /**
     * Description: 获取客户管理列表
     * @param request 请求
     * @return {@link CustomerListDTO }
     * @author 杜黎明
     * @date 2023/03/31 14:40:43
     */
    IPage<CustomerListDTO> getCustomerList(getCustomerListRequest request);

    /**
     * Description: 获取指定用户收支明细
     * @param request 请求
     * @return {@link JSONObject }
     * @author 杜黎明
     * @date 2023/03/31 16:01:56
     */
    JSONObject getCustomerTradeList(getCustomerTradeListRequest request) throws UnsupportedEncodingException;

    /**
     * Description: 获取指定客户的资源使用情况
     * @param userId 用户id
     * @return {@link JSONArray }
     * @author 杜黎明
     * @date 2023/03/31 16:12:51
     */
    JSONArray csResourceList(String userId);

    /**
     * Description: 智算获取产品列表
     * @param zsProductsSearchDTO z产品搜索dto
     * @return {@link JSONObject }
     * @author 杜黎明
     * @date 2023/05/17 17:30:32
     */
    JSONObject zsProductsList(ZsProductsSearchDTO zsProductsSearchDTO);

    Boolean zsUpdateProductStatus(zsUpdateProductStatusRequest request);

    JSONObject resourceStrategy(resourceStrategyRequest request);

    JSONObject billingStrategyList(billingStrategyRequest request);

    JSONArray billingStrategySpecGroup(Long strategyId);

    Boolean  updateBillingStrategy(updateBillingStrategyRequest request);

    JSONObject productResourcesList(productResourcesListRequest request);

    JSONObject accountDeals(accountDealsRequest request) ;

    JSONObject getOperationStatistics(orderStatisticsRequest request) throws UnsupportedEncodingException;

    JSONObject orderList(orderListRequest request);

    JSONObject listCycleBills(listCycleBillsRequest request);

    JSONObject getAccountStatistics(accountStatisticsRequest request) throws UnsupportedEncodingException;

    JSONObject accountDealsList(accountDealsListRequest request);

    JSONObject getAvailableResource(getAvailableResourceRequest request);
}
