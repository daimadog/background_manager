package com.background.manager.service;

import com.background.manager.model.BackgroundContractTemplate;
import com.background.manager.model.dto.request.template.CreateTemplateRequest;
import com.background.manager.model.dto.request.template.ModifyContractTemplateRequest;
import com.background.manager.model.dto.request.template.listQueryContractTemplateRequest;
import com.background.manager.model.dto.response.template.ContractTemplateDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 合同模板接口
 * @Author: 杜黎明
 * @Date: 2023/01/10 16:42:57
 * @Version: 1.0.0
 */
public interface BackgroundContractTemplateService extends IService<BackgroundContractTemplate> {

    /**
     * Description: 上传合同模板
     * @param request 请求
     * @return {@link String }
     * @author 杜黎明
     * @date 2023/01/10 16:53:14
     */
    String createContractTemplate(CreateTemplateRequest request);

    /**
     * Description:列表查询合同模板
     * @param request 请求
     * @return {@link List }<{@link ContractTemplateDTO }>
     * @author 杜黎明
     * @date 2023/01/10 17:25:21
     */
    List<ContractTemplateDTO> listQuery(listQueryContractTemplateRequest request);

    /**
     * Description: 修改合同模板的状态
     * @param id id
     * @author 杜黎明
     * @date 2023/01/10 17:36:01
     */
    void process(Long id);

    /**
     * Description: 修改合同模板
     * @param request 请求
     * @author 杜黎明
     * @date 2023/01/10 17:45:11
     */
    void modify(ModifyContractTemplateRequest request);

    /**
     * Description: 删除合同模板
     * @param id id
     * @author 杜黎明
     * @date 2023/01/10 17:53:35
     */
    void delete(Long id);
}
