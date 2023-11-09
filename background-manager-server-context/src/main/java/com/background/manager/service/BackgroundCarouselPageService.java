package com.background.manager.service;

import com.background.manager.model.dto.request.carousel.AddCarouselRequest;
import com.background.manager.model.dto.request.carousel.DeleteCarouselRequest;
import com.background.manager.model.dto.request.carousel.ModifyCarouselRequest;
import com.background.manager.model.dto.request.carousel.PageQueryCarouselRequest;
import com.background.manager.model.dto.response.carousel.CarouselDTO;
import com.background.manager.model.BackgroundCarouselPage;
import com.background.manager.model.dto.response.carousel.PortalStyleDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 轮播页接口类
 * @Author: 杜黎明
 * @Date: 2022/11/02 09:29:46
 * @Version: 1.0.0
 */
@Service
public interface BackgroundCarouselPageService extends IService<BackgroundCarouselPage> {

    /**
     * Description: 分页查询轮播页
     * @param request 请求
     * @return {@link IPage }<{@link CarouselDTO }>
     * @author 杜黎明
     * @date 2022/11/02 15:30:08
     */
    IPage<CarouselDTO> pageQuery(PageQueryCarouselRequest request);

    /**
     * Description: 新增轮播页
     * @param request 请求
     * @return {@link String }
     * @author 杜黎明
     * @date 2022/11/02 15:51:59
     */
    String add(AddCarouselRequest request);

    /**
     * Description: 修改轮播页请求体
     * @param request 请求
     * @author 杜黎明
     * @date 2022/11/02 16:50:46
     */
    void modify(ModifyCarouselRequest request);

    /**
     * Description: 删除轮播页请求体
     * @param id 编号
     * @author 杜黎明
     * @date 2022/11/02 16:56:43
     */
    void delete(Long id);


    /**
     * Description:遍历门户动态风格
     * @return {@link List }<{@link PortalStyleDTO }>
     * @author 杜黎明
     * @date 2023/02/08 14:56:05
     */
    List<PortalStyleDTO> listStyle();
}
