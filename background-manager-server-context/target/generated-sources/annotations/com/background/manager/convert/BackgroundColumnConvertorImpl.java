package com.background.manager.convert;

import com.background.manager.model.BackgroundCmsColumn;
import com.background.manager.model.dto.request.column.AddColumnRequest;
import com.background.manager.model.dto.request.column.ModifyColumnRequest;
import com.background.manager.model.dto.response.column.ColumnDigestDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-15T14:57:38+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class BackgroundColumnConvertorImpl implements BackgroundColumnConvertor {

    @Override
    public ColumnDigestDTO toColumnDigestDTO(BackgroundCmsColumn cmsColumn) {
        if ( cmsColumn == null ) {
            return null;
        }

        ColumnDigestDTO columnDigestDTO = new ColumnDigestDTO();

        columnDigestDTO.setId( cmsColumn.getId() );
        columnDigestDTO.setName( cmsColumn.getName() );
        columnDigestDTO.setEnName( cmsColumn.getEnName() );
        columnDigestDTO.setParentId( cmsColumn.getParentId() );
        columnDigestDTO.setStatus( cmsColumn.getStatus() );
        columnDigestDTO.setPath( cmsColumn.getPath() );
        columnDigestDTO.setOnTap( cmsColumn.getOnTap() );
        columnDigestDTO.setCreateTime( cmsColumn.getCreateTime() );

        return columnDigestDTO;
    }

    @Override
    public List<ColumnDigestDTO> toColumnDigestDTOS(List<BackgroundCmsColumn> columnList) {
        if ( columnList == null ) {
            return null;
        }

        List<ColumnDigestDTO> list = new ArrayList<ColumnDigestDTO>( columnList.size() );
        for ( BackgroundCmsColumn backgroundCmsColumn : columnList ) {
            list.add( toColumnDigestDTO( backgroundCmsColumn ) );
        }

        return list;
    }

    @Override
    public BackgroundCmsColumn toBackgroundCmsColumn(AddColumnRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundCmsColumn backgroundCmsColumn = new BackgroundCmsColumn();

        backgroundCmsColumn.setName( request.getName() );
        backgroundCmsColumn.setEnName( request.getEnName() );
        backgroundCmsColumn.setParentId( request.getParentId() );
        backgroundCmsColumn.setDescription( request.getDescription() );
        backgroundCmsColumn.setTemplatePath( request.getTemplatePath() );
        backgroundCmsColumn.setStatus( request.getStatus() );
        backgroundCmsColumn.setPath( request.getPath() );
        backgroundCmsColumn.setOnTap( request.getOnTap() );

        return backgroundCmsColumn;
    }

    @Override
    public BackgroundCmsColumn toBackgroundCmsColumn(ModifyColumnRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundCmsColumn backgroundCmsColumn = new BackgroundCmsColumn();

        backgroundCmsColumn.setId( request.getId() );
        backgroundCmsColumn.setName( request.getName() );
        backgroundCmsColumn.setEnName( request.getEnName() );
        backgroundCmsColumn.setParentId( request.getParentId() );
        backgroundCmsColumn.setDescription( request.getDescription() );
        backgroundCmsColumn.setTemplatePath( request.getTemplatePath() );
        backgroundCmsColumn.setStatus( request.getStatus() );
        backgroundCmsColumn.setPath( request.getPath() );
        backgroundCmsColumn.setOnTap( request.getOnTap() );

        return backgroundCmsColumn;
    }
}
