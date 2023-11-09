package com.background.manager.service;

import com.background.manager.model.BackgroundContract;
import com.background.manager.model.dto.request.contract.*;
import com.background.manager.model.dto.response.contract.BackgroundContractDTO;
import com.background.manager.model.dto.response.contract.BackgroundContractDigestDTO;
import com.background.manager.model.dto.response.contract.ReChargeContractDTO;
import com.background.manager.model.dto.response.receipt.BackgroundContractUserDigestDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 合同管理接口
 * @Author: 杜黎明
 * @Date: 2023/01/05 10:51:39
 * @Version: 1.0.0
 */
public interface BackgroundContractService extends IService<BackgroundContract> {

    /**
     * Description: 创建生成合同
     * @param request 请求
     * @return {@link String }
     * @author 杜黎明
     * @date 2023/01/05 13:28:16
     */
    String create(CreateContractRequest request);

    /**
     * Description: 分页查询合同
     * @param request 请求
     * @return {@link IPage }<{@link BackgroundContractDigestDTO }>
     * @author 杜黎明
     * @date 2023/01/05 14:30:24
     */
    IPage<BackgroundContractDigestDTO> pageQuery(PageQueryContractRequest request);

    /**
     * Description: 编辑修改合同
     * @param request 请求
     * @author 杜黎明
     * @date 2023/01/05 15:06:32
     */
    void edit(ModifyContractRequest request);

    /**
     * Description: 审核合同
     * @param request 请求
     * @author 杜黎明
     * @date 2023/01/05 15:35:55
     */
    void audit(AuditContactRequest request);

    /**
     * Description: 删除合同
     * @param id id
     * @author 杜黎明
     * @date 2023/01/05 15:57:08
     */
    void delete(Long id);

    /**
     * Description:查看合同详情
     * @param id id
     * @return {@link BackgroundContractDTO }
     * @author 杜黎明
     * @date 2023/01/06 10:08:11
     */
    BackgroundContractDTO getContract(Long id);

    /**
     * Description: 用户控制台-查询我的合同记录
     * @param request 请求
     * @return {@link IPage }<{@link BackgroundContractUserDigestDTO }>
     * @author 杜黎明
     * @date 2023/01/09 17:45:40
     */
    IPage<BackgroundContractUserDigestDTO> pageQueryUserContract(pageQueryUserContractRequest request);


}
