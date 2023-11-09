package com.background.manager.service;

import com.background.manager.model.dto.request.workOrder.PageQueryBackgroundWorkOrderRequest;
import com.background.manager.model.dto.response.workOrder.BackgroundWorkOrderDTO;
import com.background.manager.model.dto.response.workOrder.BackgroundWorkOrderDigestDTO;
import com.background.manager.model.work.BackgroundWorkOrder;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 工单管理接口
 * @Author: 杜黎明
 * @Date: 2023/01/19 09:17:06
 * @Version: 1.0.0
 */
public interface BackgroundWorkOrderService extends IService<BackgroundWorkOrder> {


    /**
     * Description: 工单管理列表
     * @param request 请求
     * @return {@link IPage }<{@link BackgroundWorkOrderDigestDTO }>
     * @author 杜黎明
     * @date 2023/01/19 09:36:20
     */
    IPage<BackgroundWorkOrderDigestDTO> pageQuery(PageQueryBackgroundWorkOrderRequest request);

    /**
     * Description: 关闭工单操作
     * @param id 工单编号
     * @author 杜黎明
     * @date 2023/01/28 10:23:09
     */
    void closeWorkOrder(String id);

    /**
     * Description: 工单详情
     * @param id  工单编号
     * @return {@link BackgroundWorkOrderDTO }
     * @author 杜黎明
     * @date 2023/01/29 15:02:14
     */
    BackgroundWorkOrderDTO findWorkOrder(String id);
}
