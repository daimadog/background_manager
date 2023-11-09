package com.background.manager.service;

import com.background.manager.model.dto.request.home.AddPartnersRequest;
import com.background.manager.model.dto.request.home.ModifyPartnersRequest;
import com.background.manager.model.dto.response.home.PartnersDTO;
import com.background.manager.model.BackgroundPartners;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface BackgroundPartnersService extends IService<BackgroundPartners> {

    /**
     * Description: 新增合作伙伴
     * @param request 请求
     * @return {@link String }
     * @author 杜黎明
     * @date 2022/12/15 10:46:00
     */
    String addPartners(AddPartnersRequest request);

    /**
     * Description: 列表查询合作伙伴
     * @return {@link List }<{@link PartnersDTO }>
     * @author 杜黎明
     * @date 2022/12/15 10:56:29
     */
    List<PartnersDTO> listPartners();

    /**
     * Description: 删除合作伙伴
     * @param id id
     * @author 杜黎明
     * @date 2022/12/15 11:08:03
     */
    void deletePartners(Long id);

    /**
     * Description:  编辑合作伙伴
     * @param request 请求
     * @author 杜黎明
     * @date 2022/12/15 11:17:00
     */
    void modifyPartners(ModifyPartnersRequest request);
}
