package com.background.manager.convert;

import com.background.manager.model.BackgroundContract;
import com.background.manager.model.dto.request.contract.CreateContractRequest;
import com.background.manager.model.dto.request.contract.ModifyContractRequest;
import com.background.manager.model.dto.response.contract.BackgroundContractDTO;
import com.background.manager.model.dto.response.contract.BackgroundContractDigestDTO;
import com.background.manager.model.dto.response.receipt.BackgroundContractUserDigestDTO;
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
public class BackgroundContractConvertorImpl implements BackgroundContractConvertor {

    @Override
    public BackgroundContract toBackgroundContract(CreateContractRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundContract backgroundContract = new BackgroundContract();

        backgroundContract.setName( request.getName() );
        backgroundContract.setCode( request.getCode() );
        backgroundContract.setSignTime( request.getSignTime() );
        backgroundContract.setFirstParty( request.getFirstParty() );
        backgroundContract.setSecondParty( request.getSecondParty() );
        backgroundContract.setAccountId( request.getAccountId() );
        backgroundContract.setStartTime( request.getStartTime() );
        backgroundContract.setEndTime( request.getEndTime() );
        backgroundContract.setAmount( request.getAmount() );
        backgroundContract.setCreditLimit( request.getCreditLimit() );
        backgroundContract.setStatus( request.getStatus() );
        backgroundContract.setReceiptStatus( request.getReceiptStatus() );
        backgroundContract.setContractFileUrl( request.getContractFileUrl() );

        return backgroundContract;
    }

    @Override
    public BackgroundContractDigestDTO toBackgroundContractDigestDTO(BackgroundContract backgroundContract) {
        if ( backgroundContract == null ) {
            return null;
        }

        BackgroundContractDigestDTO backgroundContractDigestDTO = new BackgroundContractDigestDTO();

        backgroundContractDigestDTO.setId( backgroundContract.getId() );
        backgroundContractDigestDTO.setName( backgroundContract.getName() );
        backgroundContractDigestDTO.setCode( backgroundContract.getCode() );
        backgroundContractDigestDTO.setSignTime( backgroundContract.getSignTime() );
        backgroundContractDigestDTO.setAmount( backgroundContract.getAmount() );
        backgroundContractDigestDTO.setCreditLimit( backgroundContract.getCreditLimit() );
        backgroundContractDigestDTO.setStartTime( backgroundContract.getStartTime() );
        backgroundContractDigestDTO.setEndTime( backgroundContract.getEndTime() );
        backgroundContractDigestDTO.setStatus( backgroundContract.getStatus() );
        backgroundContractDigestDTO.setReceiptStatus( backgroundContract.getReceiptStatus() );

        return backgroundContractDigestDTO;
    }

    @Override
    public BackgroundContract toBackgroundContract(ModifyContractRequest request) {
        if ( request == null ) {
            return null;
        }

        BackgroundContract backgroundContract = new BackgroundContract();

        backgroundContract.setId( request.getId() );
        backgroundContract.setName( request.getName() );
        backgroundContract.setCode( request.getCode() );
        backgroundContract.setSignTime( request.getSignTime() );
        backgroundContract.setFirstParty( request.getFirstParty() );
        backgroundContract.setSecondParty( request.getSecondParty() );
        backgroundContract.setAccountId( request.getAccountId() );
        backgroundContract.setStartTime( request.getStartTime() );
        backgroundContract.setEndTime( request.getEndTime() );
        backgroundContract.setAmount( request.getAmount() );
        backgroundContract.setCreditLimit( request.getCreditLimit() );
        backgroundContract.setStatus( request.getStatus() );
        backgroundContract.setReceiptStatus( request.getReceiptStatus() );
        backgroundContract.setContractFileUrl( request.getContractFileUrl() );

        return backgroundContract;
    }

    @Override
    public BackgroundContractDTO toBackgroundContactDTO(BackgroundContract backgroundContract) {
        if ( backgroundContract == null ) {
            return null;
        }

        BackgroundContractDTO backgroundContractDTO = new BackgroundContractDTO();

        backgroundContractDTO.setName( backgroundContract.getName() );
        backgroundContractDTO.setCode( backgroundContract.getCode() );
        backgroundContractDTO.setSignTime( backgroundContract.getSignTime() );
        backgroundContractDTO.setFirstParty( backgroundContract.getFirstParty() );
        backgroundContractDTO.setSecondParty( backgroundContract.getSecondParty() );
        backgroundContractDTO.setStartTime( backgroundContract.getStartTime() );
        backgroundContractDTO.setEndTime( backgroundContract.getEndTime() );
        backgroundContractDTO.setAmount( backgroundContract.getAmount() );
        backgroundContractDTO.setCreditLimit( backgroundContract.getCreditLimit() );
        backgroundContractDTO.setStatus( backgroundContract.getStatus() );
        backgroundContractDTO.setReceiptStatus( backgroundContract.getReceiptStatus() );
        backgroundContractDTO.setContractFileUrl( backgroundContract.getContractFileUrl() );

        return backgroundContractDTO;
    }

    @Override
    public BackgroundContractUserDigestDTO toBackgroundContractUserDigestDTO(BackgroundContract records) {
        if ( records == null ) {
            return null;
        }

        BackgroundContractUserDigestDTO backgroundContractUserDigestDTO = new BackgroundContractUserDigestDTO();

        backgroundContractUserDigestDTO.setId( records.getId() );
        backgroundContractUserDigestDTO.setName( records.getName() );
        backgroundContractUserDigestDTO.setCode( records.getCode() );
        backgroundContractUserDigestDTO.setStartTime( records.getStartTime() );
        backgroundContractUserDigestDTO.setEndTime( records.getEndTime() );
        backgroundContractUserDigestDTO.setAmount( records.getAmount() );
        backgroundContractUserDigestDTO.setCreditLimit( records.getCreditLimit() );
        backgroundContractUserDigestDTO.setStatus( records.getStatus() );

        return backgroundContractUserDigestDTO;
    }

    @Override
    public List<BackgroundContractUserDigestDTO> toBackgroundContractUserDigestDTOS(List<BackgroundContract> records) {
        if ( records == null ) {
            return null;
        }

        List<BackgroundContractUserDigestDTO> list = new ArrayList<BackgroundContractUserDigestDTO>( records.size() );
        for ( BackgroundContract backgroundContract : records ) {
            list.add( toBackgroundContractUserDigestDTO( backgroundContract ) );
        }

        return list;
    }
}
