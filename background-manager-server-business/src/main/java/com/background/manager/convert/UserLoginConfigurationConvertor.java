package com.background.manager.convert;

import com.background.manager.dto.request.user.modifyUserLoginConfigurationRequest;

import com.background.manager.dto.response.user.UserLoginConfigurationDTO;
import com.background.manager.model.UserLoginConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserLoginConfigurationConvertor {

    UserLoginConfiguration toUserLoginConfiguration(modifyUserLoginConfigurationRequest request);

    UserLoginConfigurationDTO toUserLoginConfigurationDTO(UserLoginConfiguration userLoginConfiguration);
}
