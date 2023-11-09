package com.background.manager.service;

import com.background.manager.model.ContractApplicationRecord;
import com.background.manager.model.dto.request.contract.ApplyRechargeContractRequest;
import com.background.manager.model.dto.request.contract.PageQueryReChargeContractRequest;
import com.background.manager.model.dto.response.contract.ReChargeContractDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


public interface ContractApplicationRecordService extends IService<ContractApplicationRecord> {

    /**
     * Description:分页查询合同充值列表
     * @param request 请求
     * @return {@link IPage }<{@link ReChargeContractDTO }>
     * @author 杜黎明
     * @date 2023/02/24 17:18:45
     */
    IPage<ReChargeContractDTO> PageQueryReChargeContract(PageQueryReChargeContractRequest request);

    /**
     * Description: 运营控制台审核用户充值记录
     * @param request
     * @author 杜黎明
     * @date 2023/02/27 09:22:37
     */
    void apply(ApplyRechargeContractRequest request);
}
