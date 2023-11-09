package com.background.manager.convert;

import com.background.manager.business.dto.ITsysUserDTO;
import com.background.manager.dto.request.user.ResourceApplicationFormDTO;
import com.background.manager.dto.response.console.CustomerListDTO;
import com.background.manager.dto.response.user.TSysUserDigestDTO;
import com.background.manager.dto.response.user.UserEnterpriseCertificationDTO;
import com.background.manager.model.ResourceApplicationForm;
import com.background.manager.model.TSysUser;
import com.background.manager.model.UserEnterpriseCertification;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TSysUserConvertor {

    TSysUserDigestDTO toTSysUserDigestDTO(TSysUser tSysUser);

    List<TSysUserDigestDTO> toTSysUserDigestDTOS(List<TSysUser> records);

    ITsysUserDTO toITsysUserDTO(TSysUser tSysUser);

    ResourceApplicationFormDTO toResourceApplicationFormDTO(ResourceApplicationForm resourceApplicationForm);

    UserEnterpriseCertificationDTO toUserEnterpriseCertificationDTO(UserEnterpriseCertification amp);

    CustomerListDTO toCustomerListDTO(TSysUser tSysUser);
}
