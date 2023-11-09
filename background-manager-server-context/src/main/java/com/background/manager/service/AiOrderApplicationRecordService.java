package com.background.manager.service;

import com.background.manager.model.AiOrderApplicationRecord;
import com.background.manager.model.dto.request.order.AuditOrderRequest;
import com.background.manager.model.dto.request.order.pageQueryAiOrderRequest;
import com.background.manager.model.dto.response.order.AiOrderApplicationRecordDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AiOrderApplicationRecordService extends IService<AiOrderApplicationRecord> {


    /**
     * Description:分页查询AI订单申请记录
     * @param request 请求
     * @return {@link IPage }<{@link AiOrderApplicationRecordDTO }>
     * @author 杜黎明
     * @date 2023/02/28 09:51:43
     */
    IPage<AiOrderApplicationRecordDTO> pageQueryAiApplication(pageQueryAiOrderRequest request);

    /**
     * Description: 审核Ai订单
     * @param request 请求
     * @author 杜黎明
     * @date 2023/02/28 09:49:00
     */
    void auditAiOrderApply(AuditOrderRequest request);
}
