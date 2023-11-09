package com.background.manager.convert;

import com.background.manager.dto.request.role.AddRoleRequest;
import com.background.manager.dto.response.role.RoleDTO;
import com.background.manager.dto.response.role.RoleDigestDTO;
import com.background.manager.model.BackgroundRole;
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
public class BackgroundRoleConvertorImpl implements BackgroundRoleConvertor {

    @Override
    public RoleDTO toRoleDTO(BackgroundRole backgroundRole) {
        if ( backgroundRole == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId( backgroundRole.getId() );
        roleDTO.setRoleName( backgroundRole.getRoleName() );

        return roleDTO;
    }

    @Override
    public List<RoleDTO> toRoleDTOS(List<BackgroundRole> backgroundRoles) {
        if ( backgroundRoles == null ) {
            return null;
        }

        List<RoleDTO> list = new ArrayList<RoleDTO>( backgroundRoles.size() );
        for ( BackgroundRole backgroundRole : backgroundRoles ) {
            list.add( toRoleDTO( backgroundRole ) );
        }

        return list;
    }

    @Override
    public RoleDigestDTO toRoleDigestDTO(BackgroundRole backgroundRole) {
        if ( backgroundRole == null ) {
            return null;
        }

        RoleDigestDTO roleDigestDTO = new RoleDigestDTO();

        roleDigestDTO.setId( backgroundRole.getId() );
        roleDigestDTO.setRoleName( backgroundRole.getRoleName() );
        roleDigestDTO.setEnName( backgroundRole.getEnName() );
        roleDigestDTO.setStatus( backgroundRole.getStatus() );
        roleDigestDTO.setCreateTime( backgroundRole.getCreateTime() );

        return roleDigestDTO;
    }

    @Override
    public List<RoleDigestDTO> toRoleDigestDTOS(List<BackgroundRole> backgroundRoles) {
        if ( backgroundRoles == null ) {
            return null;
        }

        List<RoleDigestDTO> list = new ArrayList<RoleDigestDTO>( backgroundRoles.size() );
        for ( BackgroundRole backgroundRole : backgroundRoles ) {
            list.add( toRoleDigestDTO( backgroundRole ) );
        }

        return list;
    }

    @Override
    public BackgroundRole toBackgroundRole(AddRoleRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundRole backgroundRole = new BackgroundRole();

        backgroundRole.setRoleName( request.getRoleName() );
        backgroundRole.setEnName( request.getEnName() );

        return backgroundRole;
    }
}
