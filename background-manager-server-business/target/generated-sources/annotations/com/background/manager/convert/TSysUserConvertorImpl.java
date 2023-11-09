package com.background.manager.convert;

import com.background.manager.business.dto.ITsysUserDTO;
import com.background.manager.dto.request.user.ResourceApplicationFormDTO;
import com.background.manager.dto.response.console.CustomerListDTO;
import com.background.manager.dto.response.user.TSysUserDigestDTO;
import com.background.manager.dto.response.user.UserEnterpriseCertificationDTO;
import com.background.manager.model.ResourceApplicationForm;
import com.background.manager.model.TSysUser;
import com.background.manager.model.UserEnterpriseCertification;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-15T14:57:24+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class TSysUserConvertorImpl implements TSysUserConvertor {

    @Override
    public TSysUserDigestDTO toTSysUserDigestDTO(TSysUser tSysUser) {
        if ( tSysUser == null ) {
            return null;
        }

        TSysUserDigestDTO tSysUserDigestDTO = new TSysUserDigestDTO();

        tSysUserDigestDTO.setUserId( tSysUser.getUserId() );
        tSysUserDigestDTO.setUsername( tSysUser.getUsername() );
        tSysUserDigestDTO.setCompanyName( tSysUser.getCompanyName() );
        tSysUserDigestDTO.setContactName( tSysUser.getContactName() );
        tSysUserDigestDTO.setContactPhone( tSysUser.getContactPhone() );
        tSysUserDigestDTO.setContactEmail( tSysUser.getContactEmail() );
        tSysUserDigestDTO.setLastLogin( tSysUser.getLastLogin() );
        tSysUserDigestDTO.setRegisterTime( tSysUser.getRegisterTime() );
        tSysUserDigestDTO.setApplyStatus( tSysUser.getApplyStatus() );

        return tSysUserDigestDTO;
    }

    @Override
    public List<TSysUserDigestDTO> toTSysUserDigestDTOS(List<TSysUser> records) {
        if ( records == null ) {
            return null;
        }

        List<TSysUserDigestDTO> list = new ArrayList<TSysUserDigestDTO>( records.size() );
        for ( TSysUser tSysUser : records ) {
            list.add( toTSysUserDigestDTO( tSysUser ) );
        }

        return list;
    }

    @Override
    public ITsysUserDTO toITsysUserDTO(TSysUser tSysUser) {
        if ( tSysUser == null ) {
            return null;
        }

        ITsysUserDTO iTsysUserDTO = new ITsysUserDTO();

        iTsysUserDTO.setUsername( tSysUser.getUsername() );
        iTsysUserDTO.setCompanyName( tSysUser.getCompanyName() );
        iTsysUserDTO.setContactName( tSysUser.getContactName() );
        iTsysUserDTO.setContactPhone( tSysUser.getContactPhone() );
        iTsysUserDTO.setContactEmail( tSysUser.getContactEmail() );
        iTsysUserDTO.setLastLogin( tSysUser.getLastLogin() );
        iTsysUserDTO.setRegisterTime( tSysUser.getRegisterTime() );
        iTsysUserDTO.setApplyStatus( tSysUser.getApplyStatus() );
        iTsysUserDTO.setZsUserId( tSysUser.getZsUserId() );
        iTsysUserDTO.setZsOrgSid( tSysUser.getZsOrgSid() );

        return iTsysUserDTO;
    }

    @Override
    public ResourceApplicationFormDTO toResourceApplicationFormDTO(ResourceApplicationForm resourceApplicationForm) {
        if ( resourceApplicationForm == null ) {
            return null;
        }

        ResourceApplicationFormDTO resourceApplicationFormDTO = new ResourceApplicationFormDTO();

        resourceApplicationFormDTO.setId( resourceApplicationForm.getId() );
        resourceApplicationFormDTO.setCompanyName( resourceApplicationForm.getCompanyName() );
        resourceApplicationFormDTO.setProjectName( resourceApplicationForm.getProjectName() );
        resourceApplicationFormDTO.setProjectType( resourceApplicationForm.getProjectType() );
        resourceApplicationFormDTO.setContactName( resourceApplicationForm.getContactName() );
        resourceApplicationFormDTO.setContactPhone( resourceApplicationForm.getContactPhone() );
        resourceApplicationFormDTO.setProjectIntroduce( resourceApplicationForm.getProjectIntroduce() );
        resourceApplicationFormDTO.setProjectRequire( resourceApplicationForm.getProjectRequire() );
        resourceApplicationFormDTO.setConclusion( resourceApplicationForm.getConclusion() );
        resourceApplicationFormDTO.setUserId( resourceApplicationForm.getUserId() );
        resourceApplicationFormDTO.setApplicantTime( resourceApplicationForm.getApplicantTime() );
        resourceApplicationFormDTO.setApplicantStatus( resourceApplicationForm.getApplicantStatus() );

        return resourceApplicationFormDTO;
    }

    @Override
    public UserEnterpriseCertificationDTO toUserEnterpriseCertificationDTO(UserEnterpriseCertification amp) {
        if ( amp == null ) {
            return null;
        }

        UserEnterpriseCertificationDTO userEnterpriseCertificationDTO = new UserEnterpriseCertificationDTO();

        userEnterpriseCertificationDTO.setId( amp.getId() );
        userEnterpriseCertificationDTO.setCompanyName( amp.getCompanyName() );
        userEnterpriseCertificationDTO.setCorporateName( amp.getCorporateName() );
        userEnterpriseCertificationDTO.setTaxNum( amp.getTaxNum() );
        userEnterpriseCertificationDTO.setBusinessLicenseUrl( amp.getBusinessLicenseUrl() );
        userEnterpriseCertificationDTO.setCertificationStatus( amp.getCertificationStatus() );
        userEnterpriseCertificationDTO.setCreateTime( amp.getCreateTime() );

        return userEnterpriseCertificationDTO;
    }

    @Override
    public CustomerListDTO toCustomerListDTO(TSysUser tSysUser) {
        if ( tSysUser == null ) {
            return null;
        }

        CustomerListDTO customerListDTO = new CustomerListDTO();

        customerListDTO.setUserId( tSysUser.getUserId() );
        customerListDTO.setUsername( tSysUser.getUsername() );
        customerListDTO.setCompanyName( tSysUser.getCompanyName() );
        customerListDTO.setContactName( tSysUser.getContactName() );
        customerListDTO.setContactPhone( tSysUser.getContactPhone() );
        customerListDTO.setRegisterTime( tSysUser.getRegisterTime() );

        return customerListDTO;
    }
}
