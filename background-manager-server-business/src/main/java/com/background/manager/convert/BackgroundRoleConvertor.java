package com.background.manager.convert;

import com.background.manager.dto.request.role.AddRoleRequest;
import com.background.manager.dto.response.role.RoleDTO;
import com.background.manager.dto.response.role.RoleDigestDTO;
import com.background.manager.model.BackgroundRole;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @Description: 后台用户角色转换器
 * @Author: 杜黎明
 * @Date: 2022/10/08 16:13:42
 * @Version: 1.0.0
 */
@Mapper(componentModel = "spring")
public interface BackgroundRoleConvertor {

    RoleDTO toRoleDTO(BackgroundRole backgroundRole);

    List<RoleDTO> toRoleDTOS(List<BackgroundRole> backgroundRoles);

    RoleDigestDTO toRoleDigestDTO(BackgroundRole backgroundRole);

    List<RoleDigestDTO> toRoleDigestDTOS(List<BackgroundRole> backgroundRoles);

    BackgroundRole toBackgroundRole(AddRoleRequest request);
}
