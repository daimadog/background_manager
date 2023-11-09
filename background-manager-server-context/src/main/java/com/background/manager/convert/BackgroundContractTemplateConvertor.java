package com.background.manager.convert;

import com.background.manager.model.BackgroundContractTemplate;
import com.background.manager.model.dto.request.template.CreateTemplateRequest;
import com.background.manager.model.dto.request.template.ModifyContractTemplateRequest;
import com.background.manager.model.dto.response.template.ContractTemplateDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @Description: 合同模板映射器
 * @Author: 杜黎明
 * @Date: 2023/01/10 16:55:02
 * @Version: 1.0.0
 */
@Mapper(componentModel = "spring")
public interface BackgroundContractTemplateConvertor {


    BackgroundContractTemplate toBackgroundContractTemplate(CreateTemplateRequest request);

    ContractTemplateDTO toContractTemplateDTO (BackgroundContractTemplate backgroundContractTemplate);

    List<ContractTemplateDTO> toContractTemplateDTOS(List<BackgroundContractTemplate> backgroundContractTemplates);

    BackgroundContractTemplate toBackgroundContractTemplate(ModifyContractTemplateRequest request);
}
