package com.background.manager.service;

import com.background.manager.model.dto.request.column.AddColumnRequest;
import com.background.manager.model.dto.request.column.ListQueryColumnRequest;
import com.background.manager.model.dto.request.column.ModifyColumnRequest;
import com.background.manager.model.dto.response.column.ColumnDigestDTO;
import com.background.manager.model.BackgroundCmsColumn;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 栏目管理接口
 * @Author: 杜黎明
 * @Date: 2022/10/26 10:37:11
 * @Version: 1.0.0
 */
public interface BackgroundColumnService extends IService<BackgroundCmsColumn> {

    /**
     * Description: 树形列表查询栏目管理
     * @param request 查询栏目管理请求体
     * @return {@link List }<{@link ColumnDigestDTO }>
     * @author 杜黎明
     * @date 2022/10/26 11:19:14
     */
    List<ColumnDigestDTO> listQuery(ListQueryColumnRequest request);

    /**
     * Description: 新增栏目
     * @param request 新增栏目请求体
     * @return {@link String }
     * @author 杜黎明
     * @date 2022/10/26 13:41:41
     */
    String add(AddColumnRequest request);

    /**
     * Description: 修改栏目
     * @param request 请求
     * @author 杜黎明
     * @date 2022/10/26 13:54:40
     */
    void modify(ModifyColumnRequest request);

    /**
     * Description: 删除栏目
     * @param id id
     * @author 杜黎明
     * @date 2022/10/26 13:58:13
     */
    void delete(Long id);

    /**
     * Description: 栏目菜单向上移动
     * @param id id
     * @author 杜黎明
     * @date 2023/03/21 13:36:43
     */
    void moveUpColumn(Long id);

    /**
     * Description: 栏目菜单向下移动
     * @param id id
     * @author 杜黎明
     * @date 2023/03/21 13:37:03
     */
    void moveDownColumn(Long id);
}
