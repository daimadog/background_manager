package com.background.manager.service;

import com.background.manager.model.HpcOrderApplicationRecord;
import com.background.manager.model.dto.request.order.AuditOrderRequest;
import com.background.manager.model.dto.request.order.pageQueryHpcOrderRequest;
import com.background.manager.model.dto.response.order.HpcOrderApplicationRecordDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface HpcOrderApplicationRecordService extends IService<HpcOrderApplicationRecord> {


    /**
     * Description: 分页查询HPC订单申请记录
     * @param request 请求
     * @return {@link IPage }<{@link HpcOrderApplicationRecordDTO }>
     * @author 杜黎明
     * @date 2023/02/28 09:19:27
     */
    IPage<HpcOrderApplicationRecordDTO> pageQueryHpcApplication(pageQueryHpcOrderRequest request);

    /**
     * Description: 审核Hpc订单申请
     * @param request 请求
     * @author 杜黎明
     * @date 2023/02/28 09:47:28
     */
    void auditHpcOrderApply(AuditOrderRequest request);
}
