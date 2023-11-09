package com.background.manager.convert;

import com.background.manager.dto.request.permission.AddPermsRequest;
import com.background.manager.dto.request.permission.ModifyPermsRequest;
import com.background.manager.dto.response.permission.PermissionDTO;
import com.background.manager.dto.response.permission.PermissionDigestDTO;
import com.background.manager.model.BackgroundMenu;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @Description: 权限菜单转换器
 * @Author: 杜黎明
 * @Date: 2022/09/30 15:58:24
 * @Version: 1.0.0
 */
@Mapper(componentModel = "spring")
public interface BackgroundMenuConvertor {

    PermissionDTO toPermissionDTO (BackgroundMenu backgroundMenu);

    List<PermissionDTO> toPermissionDTOList(List<BackgroundMenu> backgroundMenuList);

    PermissionDigestDTO toPermissionDigestDTO(BackgroundMenu backgroundMenu);

    List<PermissionDigestDTO> toPermissionDigestDTOS(List<BackgroundMenu> records);

    BackgroundMenu toBackgroundMenu(AddPermsRequest request);

    BackgroundMenu toBackgroundMenu(ModifyPermsRequest request);
}
