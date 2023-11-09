package com.background.manager.convert;

import com.background.manager.dto.request.permission.AddPermsRequest;
import com.background.manager.dto.request.permission.ModifyPermsRequest;
import com.background.manager.dto.response.permission.PermissionDTO;
import com.background.manager.dto.response.permission.PermissionDigestDTO;
import com.background.manager.model.BackgroundMenu;
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
public class BackgroundMenuConvertorImpl implements BackgroundMenuConvertor {

    @Override
    public PermissionDTO toPermissionDTO(BackgroundMenu backgroundMenu) {
        if ( backgroundMenu == null ) {
            return null;
        }

        PermissionDTO permissionDTO = new PermissionDTO();

        permissionDTO.setId( backgroundMenu.getId() );
        permissionDTO.setMenuName( backgroundMenu.getMenuName() );
        permissionDTO.setPath( backgroundMenu.getPath() );
        permissionDTO.setPerms( backgroundMenu.getPerms() );
        permissionDTO.setParentId( backgroundMenu.getParentId() );

        return permissionDTO;
    }

    @Override
    public List<PermissionDTO> toPermissionDTOList(List<BackgroundMenu> backgroundMenuList) {
        if ( backgroundMenuList == null ) {
            return null;
        }

        List<PermissionDTO> list = new ArrayList<PermissionDTO>( backgroundMenuList.size() );
        for ( BackgroundMenu backgroundMenu : backgroundMenuList ) {
            list.add( toPermissionDTO( backgroundMenu ) );
        }

        return list;
    }

    @Override
    public PermissionDigestDTO toPermissionDigestDTO(BackgroundMenu backgroundMenu) {
        if ( backgroundMenu == null ) {
            return null;
        }

        PermissionDigestDTO permissionDigestDTO = new PermissionDigestDTO();

        permissionDigestDTO.setId( backgroundMenu.getId() );
        permissionDigestDTO.setMenuName( backgroundMenu.getMenuName() );
        permissionDigestDTO.setType( backgroundMenu.getType() );
        permissionDigestDTO.setParentId( backgroundMenu.getParentId() );
        permissionDigestDTO.setIcon( backgroundMenu.getIcon() );
        permissionDigestDTO.setPath( backgroundMenu.getPath() );
        permissionDigestDTO.setPerms( backgroundMenu.getPerms() );
        permissionDigestDTO.setStatus( backgroundMenu.getStatus() );

        return permissionDigestDTO;
    }

    @Override
    public List<PermissionDigestDTO> toPermissionDigestDTOS(List<BackgroundMenu> records) {
        if ( records == null ) {
            return null;
        }

        List<PermissionDigestDTO> list = new ArrayList<PermissionDigestDTO>( records.size() );
        for ( BackgroundMenu backgroundMenu : records ) {
            list.add( toPermissionDigestDTO( backgroundMenu ) );
        }

        return list;
    }

    @Override
    public BackgroundMenu toBackgroundMenu(AddPermsRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundMenu backgroundMenu = new BackgroundMenu();

        backgroundMenu.setMenuName( request.getMenuName() );
        backgroundMenu.setPath( request.getPath() );
        if ( request.getParentId() != null ) {
            backgroundMenu.setParentId( Long.parseLong( request.getParentId() ) );
        }
        backgroundMenu.setType( request.getType() );
        backgroundMenu.setStatus( request.getStatus() );
        backgroundMenu.setPerms( request.getPerms() );

        return backgroundMenu;
    }

    @Override
    public BackgroundMenu toBackgroundMenu(ModifyPermsRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundMenu backgroundMenu = new BackgroundMenu();

        backgroundMenu.setId( request.getId() );
        backgroundMenu.setMenuName( request.getMenuName() );
        backgroundMenu.setPath( request.getPath() );
        if ( request.getParentId() != null ) {
            backgroundMenu.setParentId( Long.parseLong( request.getParentId() ) );
        }
        backgroundMenu.setType( request.getType() );
        backgroundMenu.setStatus( request.getStatus() );
        backgroundMenu.setPerms( request.getPerms() );

        return backgroundMenu;
    }
}
