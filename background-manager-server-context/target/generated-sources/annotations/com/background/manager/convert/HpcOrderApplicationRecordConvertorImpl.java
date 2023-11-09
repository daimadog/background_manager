package com.background.manager.convert;

import com.background.manager.model.HpcOrderApplicationRecord;
import com.background.manager.model.dto.response.order.HpcOrderApplicationRecordDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-15T14:57:38+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class HpcOrderApplicationRecordConvertorImpl implements HpcOrderApplicationRecordConvertor {

    @Override
    public HpcOrderApplicationRecordDTO toHpcOrderApplicationRecordDTO(HpcOrderApplicationRecord record) {
        if ( record == null ) {
            return null;
        }

        HpcOrderApplicationRecordDTO hpcOrderApplicationRecordDTO = new HpcOrderApplicationRecordDTO();

        hpcOrderApplicationRecordDTO.setId( record.getId() );
        hpcOrderApplicationRecordDTO.setOrderId( record.getOrderId() );
        hpcOrderApplicationRecordDTO.setServiceType( record.getServiceType() );
        hpcOrderApplicationRecordDTO.setAmount( record.getAmount() );
        hpcOrderApplicationRecordDTO.setApplyType( record.getApplyType() );
        hpcOrderApplicationRecordDTO.setApplyStatus( record.getApplyStatus() );
        hpcOrderApplicationRecordDTO.setApplyTime( record.getApplyTime() );
        hpcOrderApplicationRecordDTO.setRenewalTime( record.getRenewalTime() );
        hpcOrderApplicationRecordDTO.setRemark( record.getRemark() );

        return hpcOrderApplicationRecordDTO;
    }
}
