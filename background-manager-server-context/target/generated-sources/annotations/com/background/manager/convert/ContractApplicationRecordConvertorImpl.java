package com.background.manager.convert;

import com.background.manager.model.ContractApplicationRecord;
import com.background.manager.model.dto.response.contract.ReChargeContractDTO;
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
public class ContractApplicationRecordConvertorImpl implements ContractApplicationRecordConvertor {

    @Override
    public ReChargeContractDTO toReChargeContractDTO(ContractApplicationRecord contractOperationNote) {
        if ( contractOperationNote == null ) {
            return null;
        }

        ReChargeContractDTO reChargeContractDTO = new ReChargeContractDTO();

        reChargeContractDTO.setId( contractOperationNote.getId() );
        reChargeContractDTO.setRechargeAmount( contractOperationNote.getRechargeAmount() );
        reChargeContractDTO.setRechargeTime( contractOperationNote.getRechargeTime() );
        reChargeContractDTO.setRechargeStatus( contractOperationNote.getRechargeStatus() );
        reChargeContractDTO.setRemark( contractOperationNote.getRemark() );
        reChargeContractDTO.setCreator( contractOperationNote.getCreator() );
        reChargeContractDTO.setCreateTime( contractOperationNote.getCreateTime() );

        return reChargeContractDTO;
    }

    @Override
    public List<ReChargeContractDTO> toReChargeContractDTOList(List<ContractApplicationRecord> records) {
        if ( records == null ) {
            return null;
        }

        List<ReChargeContractDTO> list = new ArrayList<ReChargeContractDTO>( records.size() );
        for ( ContractApplicationRecord contractApplicationRecord : records ) {
            list.add( toReChargeContractDTO( contractApplicationRecord ) );
        }

        return list;
    }
}
