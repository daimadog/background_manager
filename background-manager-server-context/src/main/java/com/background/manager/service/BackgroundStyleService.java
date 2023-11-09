package com.background.manager.service;

import com.background.manager.model.BackgroundStyle;
import com.background.manager.model.dto.request.style.ListQueryBackgroundStyleRequest;
import com.background.manager.model.dto.request.style.ModifyBackgroundStyleRequest;
import com.background.manager.model.dto.response.style.BackgroundStyleDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface BackgroundStyleService extends IService<BackgroundStyle> {


    /**
     * Description: 列表查询样式风格
     * @param request 请求
     * @return {@link List }<{@link BackgroundStyleDTO }>
     * @author 杜黎明
     * @date 2023/02/09 10:49:31
     */
    List<BackgroundStyleDTO> listQuery(ListQueryBackgroundStyleRequest request);

    /**
     * Description: 修改样式风格
     * @param request 请求
     * @author 杜黎明
     * @date 2023/02/09 10:58:25
     */
    void modify(ModifyBackgroundStyleRequest request);
}
