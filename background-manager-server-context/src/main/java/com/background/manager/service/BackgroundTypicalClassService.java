package com.background.manager.service;

import com.background.manager.model.dto.request.home.AddTypicalClassRequest;
import com.background.manager.model.dto.request.home.ModifyTypicalClassRequest;
import com.background.manager.model.dto.request.home.PageQueryTypicalClassRequest;
import com.background.manager.model.dto.response.home.TypicalClassDTO;
import com.background.manager.model.dto.response.home.TypicalClassDigestDTO;
import com.background.manager.model.BackgroundTypicalClass;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface BackgroundTypicalClassService extends IService<BackgroundTypicalClass> {

    /**
     * Description: 新增典型案例
     * @param request 请求
     * @return {@link String }
     * @author 杜黎明
     * @date 2022/12/14 17:25:03
     */
    String addTypicalClass(AddTypicalClassRequest request);

    /**
     * Description: 分页查询典型案例
     * @param request 请求
     * @return {@link IPage }<{@link TypicalClassDigestDTO }>
     * @author 杜黎明
     * @date 2022/12/15 09:08:57
     */
    IPage<TypicalClassDigestDTO> pageQuery(PageQueryTypicalClassRequest request);

    /**
     * Description: 查询典型案例
     * @param id id
     * @return {@link TypicalClassDTO }
     * @author 杜黎明
     * @date 2022/12/15 09:31:09
     */
    TypicalClassDTO getTypicalClass(Long id);

    /**
     * Description: 编辑典型案例请求体
     * @param request 请求
     * @author 杜黎明
     * @date 2022/12/15 09:52:30
     */
    void modify(ModifyTypicalClassRequest request);

    /**
     * Description: 删除典型案例
     * @param id id
     * @author 杜黎明
     * @date 2022/12/15 10:13:09
     */
    void delete(Long id);
}
