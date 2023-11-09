package com.background.manager.convert;

import com.background.manager.dto.response.log.LoginLogDigestDTO;
import com.background.manager.dto.response.log.OperationLogDigestDTO;
import com.background.manager.model.BackgroundLoginLog;
import com.background.manager.model.BackgroundOperationLog;
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
public class BackgroundLogConvertorImpl implements BackgroundLogConvertor {

    @Override
    public LoginLogDigestDTO LoginLogDigestDTO(BackgroundLoginLog backgroundLoginLog) {
        if ( backgroundLoginLog == null ) {
            return null;
        }

        LoginLogDigestDTO loginLogDigestDTO = new LoginLogDigestDTO();

        loginLogDigestDTO.setId( backgroundLoginLog.getId() );
        loginLogDigestDTO.setLoginId( backgroundLoginLog.getLoginId() );
        loginLogDigestDTO.setStatus( backgroundLoginLog.getStatus() );
        loginLogDigestDTO.setCreatorIp( backgroundLoginLog.getCreatorIp() );
        loginLogDigestDTO.setCreatorAddress( backgroundLoginLog.getCreatorAddress() );
        loginLogDigestDTO.setMessage( backgroundLoginLog.getMessage() );
        loginLogDigestDTO.setCreateTime( backgroundLoginLog.getCreateTime() );
        loginLogDigestDTO.setLogFlag( backgroundLoginLog.getLogFlag() );

        return loginLogDigestDTO;
    }

    @Override
    public List<LoginLogDigestDTO> LoginLogDigestDTOs(List<BackgroundLoginLog> records) {
        if ( records == null ) {
            return null;
        }

        List<LoginLogDigestDTO> list = new ArrayList<LoginLogDigestDTO>( records.size() );
        for ( BackgroundLoginLog backgroundLoginLog : records ) {
            list.add( LoginLogDigestDTO( backgroundLoginLog ) );
        }

        return list;
    }

    @Override
    public OperationLogDigestDTO toOperationLogDTO(BackgroundOperationLog backgroundOperationLog) {
        if ( backgroundOperationLog == null ) {
            return null;
        }

        OperationLogDigestDTO operationLogDigestDTO = new OperationLogDigestDTO();

        operationLogDigestDTO.setId( backgroundOperationLog.getId() );
        operationLogDigestDTO.setRequestName( backgroundOperationLog.getRequestName() );
        operationLogDigestDTO.setRequestApi( backgroundOperationLog.getRequestApi() );
        operationLogDigestDTO.setRequestType( backgroundOperationLog.getRequestType() );
        operationLogDigestDTO.setCreator( backgroundOperationLog.getCreator() );
        operationLogDigestDTO.setCreatorIp( backgroundOperationLog.getCreatorIp() );
        operationLogDigestDTO.setCreatorAddress( backgroundOperationLog.getCreatorAddress() );
        operationLogDigestDTO.setCostTime( backgroundOperationLog.getCostTime() );
        operationLogDigestDTO.setCreateTime( backgroundOperationLog.getCreateTime() );
        operationLogDigestDTO.setRequestStatus( backgroundOperationLog.getRequestStatus() );
        operationLogDigestDTO.setResMsg( backgroundOperationLog.getResMsg() );

        return operationLogDigestDTO;
    }

    @Override
    public List<OperationLogDigestDTO> toOperationLogDTOS(List<BackgroundOperationLog> records) {
        if ( records == null ) {
            return null;
        }

        List<OperationLogDigestDTO> list = new ArrayList<OperationLogDigestDTO>( records.size() );
        for ( BackgroundOperationLog backgroundOperationLog : records ) {
            list.add( toOperationLogDTO( backgroundOperationLog ) );
        }

        return list;
    }
}
