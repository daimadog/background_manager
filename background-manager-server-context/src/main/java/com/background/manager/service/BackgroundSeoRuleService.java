package com.background.manager.service;

import com.background.manager.model.BackgroundSeoRule;
import com.background.manager.model.dto.request.seo.AddSeoRuleRequest;
import com.background.manager.model.dto.request.seo.ModifySeoRuleRequest;
import com.background.manager.model.dto.request.seo.PageQuerySeoRuleRequest;
import com.background.manager.model.dto.response.seo.BackgroundSeoRuleDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface BackgroundSeoRuleService extends IService<BackgroundSeoRule> {

    /**
     * Description: 新增seo配置
     * @param request seo配置规则
     * @author 杜黎明
     * @date 2023/02/15 11:03:47
     */
    void add(AddSeoRuleRequest request);

    /**
     * Description: 删除seo配置
     * @param id id
     * @author 杜黎明
     * @date 2023/02/15 11:12:52
     */
    void delete(Long id);

    /**
     * Description: 分页查询seo配置
     * @return {@link IPage }<{@link BackgroundSeoRuleDTO }>
     * @author 杜黎明
     * @date 2023/02/15 11:28:51
     */
    IPage<BackgroundSeoRuleDTO> pageQuery(PageQuerySeoRuleRequest request);

    /**
     * Description: 门户列表查询seo配置
     * @return {@link List }<{@link BackgroundSeoRuleDTO }>
     * @author 杜黎明
     * @date 2023/02/15 16:54:15
     */
    List<BackgroundSeoRuleDTO> listQuery();

    /**
     * Description: 编辑seo配置
     * @param request 请求
     * @author 杜黎明
     * @date 2023/03/24 16:50:55
     */
    void modify(ModifySeoRuleRequest request);
}

