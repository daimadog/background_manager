package com.background.manager.convert.resource;

import com.background.manager.model.dto.request.resource.AddCustomerUsageRequest;
import com.background.manager.model.dto.request.resource.AddResourceDailyUsage;
import com.background.manager.model.dto.request.resource.AddResourceRequest;
import com.background.manager.model.dto.request.resource.AddWorkRunningUsageRequest;
import com.background.manager.model.dto.request.resource.ModifyCustomerUsageRequest;
import com.background.manager.model.dto.request.resource.ModifyResourceDailyUsageRequest;
import com.background.manager.model.dto.request.resource.ModifyResourceRequest;
import com.background.manager.model.dto.request.resource.ModifyWorkRunningUsageRequest;
import com.background.manager.model.dto.response.resource.CustomerUsageDTO;
import com.background.manager.model.dto.response.resource.ResourceDailyUsageDTO;
import com.background.manager.model.dto.response.resource.ResourceDailyValueDTO;
import com.background.manager.model.dto.response.resource.ResourceDigestDTO;
import com.background.manager.model.dto.response.resource.WorkRunningUsageDTO;
import com.background.manager.model.resource.ClusterResource;
import com.background.manager.model.resource.CustomerUsage;
import com.background.manager.model.resource.ResourceDailyUsage;
import com.background.manager.model.resource.ResourceDailyValue;
import com.background.manager.model.resource.WorkRunningUsage;
import java.sql.Date;
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
public class ResourceConvertorImpl implements ResourceConvertor {

    @Override
    public ClusterResource toResource(AddResourceRequest request) {
        if ( request == null ) {
            return null;
        }

        ClusterResource clusterResource = new ClusterResource();

        clusterResource.setType( request.getType() );
        clusterResource.setResourceIndex( request.getResourceIndex() );
        clusterResource.setResourceName( request.getResourceName() );
        clusterResource.setUnit( request.getUnit() );
        clusterResource.setValue( request.getValue() );

        return clusterResource;
    }

    @Override
    public ResourceDigestDTO toResourceDigestDTO(ClusterResource resource) {
        if ( resource == null ) {
            return null;
        }

        ResourceDigestDTO resourceDigestDTO = new ResourceDigestDTO();

        resourceDigestDTO.setId( resource.getId() );
        resourceDigestDTO.setType( resource.getType() );
        resourceDigestDTO.setResourceIndex( resource.getResourceIndex() );
        resourceDigestDTO.setResourceName( resource.getResourceName() );
        resourceDigestDTO.setUnit( resource.getUnit() );
        resourceDigestDTO.setValue( resource.getValue() );

        return resourceDigestDTO;
    }

    @Override
    public List<ResourceDigestDTO> toResourceDigestDTOS(List<ClusterResource> records) {
        if ( records == null ) {
            return null;
        }

        List<ResourceDigestDTO> list = new ArrayList<ResourceDigestDTO>( records.size() );
        for ( ClusterResource clusterResource : records ) {
            list.add( toResourceDigestDTO( clusterResource ) );
        }

        return list;
    }

    @Override
    public ClusterResource toResource(ModifyResourceRequest request) {
        if ( request == null ) {
            return null;
        }

        ClusterResource clusterResource = new ClusterResource();

        clusterResource.setId( request.getId() );
        clusterResource.setType( request.getType() );
        clusterResource.setResourceIndex( request.getResourceIndex() );
        clusterResource.setResourceName( request.getResourceName() );
        clusterResource.setUnit( request.getUnit() );
        clusterResource.setValue( request.getValue() );

        return clusterResource;
    }

    @Override
    public ResourceDailyUsage toResourceDailyUsage(AddResourceDailyUsage request) {
        if ( request == null ) {
            return null;
        }

        ResourceDailyUsage resourceDailyUsage = new ResourceDailyUsage();

        if ( request.getDate() != null ) {
            resourceDailyUsage.setDate( new Date( request.getDate().getTime() ) );
        }
        resourceDailyUsage.setType( request.getType() );

        return resourceDailyUsage;
    }

    @Override
    public ResourceDailyUsageDTO toResourceDailyUsageDTO(ResourceDailyUsage resourceDailyUsage) {
        if ( resourceDailyUsage == null ) {
            return null;
        }

        ResourceDailyUsageDTO resourceDailyUsageDTO = new ResourceDailyUsageDTO();

        resourceDailyUsageDTO.setDate( resourceDailyUsage.getDate() );
        resourceDailyUsageDTO.setType( resourceDailyUsage.getType() );

        return resourceDailyUsageDTO;
    }

    @Override
    public WorkRunningUsage toWorkRunningUsage(AddWorkRunningUsageRequest request) {
        if ( request == null ) {
            return null;
        }

        WorkRunningUsage workRunningUsage = new WorkRunningUsage();

        workRunningUsage.setName( request.getName() );
        workRunningUsage.setUsername( request.getUsername() );
        workRunningUsage.setCustomerName( request.getCustomerName() );
        workRunningUsage.setWorkUsage( request.getWorkUsage() );
        workRunningUsage.setStatus( request.getStatus() );
        workRunningUsage.setRunningNode( request.getRunningNode() );
        workRunningUsage.setLatitude( request.getLatitude() );
        workRunningUsage.setLongitude( request.getLongitude() );

        return workRunningUsage;
    }

    @Override
    public WorkRunningUsageDTO toWorkRunningUsageDTO(WorkRunningUsage workRunningUsage) {
        if ( workRunningUsage == null ) {
            return null;
        }

        WorkRunningUsageDTO workRunningUsageDTO = new WorkRunningUsageDTO();

        workRunningUsageDTO.setId( workRunningUsage.getId() );
        workRunningUsageDTO.setName( workRunningUsage.getName() );
        workRunningUsageDTO.setUsername( workRunningUsage.getUsername() );
        workRunningUsageDTO.setCustomerName( workRunningUsage.getCustomerName() );
        workRunningUsageDTO.setWorkUsage( workRunningUsage.getWorkUsage() );
        workRunningUsageDTO.setStatus( workRunningUsage.getStatus() );
        workRunningUsageDTO.setRunningNode( workRunningUsage.getRunningNode() );
        workRunningUsageDTO.setLatitude( workRunningUsage.getLatitude() );
        workRunningUsageDTO.setLongitude( workRunningUsage.getLongitude() );

        return workRunningUsageDTO;
    }

    @Override
    public List<WorkRunningUsageDTO> toWorkRunningUsageDTOList(List<WorkRunningUsage> workRunningUsageList) {
        if ( workRunningUsageList == null ) {
            return null;
        }

        List<WorkRunningUsageDTO> list = new ArrayList<WorkRunningUsageDTO>( workRunningUsageList.size() );
        for ( WorkRunningUsage workRunningUsage : workRunningUsageList ) {
            list.add( toWorkRunningUsageDTO( workRunningUsage ) );
        }

        return list;
    }

    @Override
    public CustomerUsage toCustomerUsage(AddCustomerUsageRequest request) {
        if ( request == null ) {
            return null;
        }

        CustomerUsage customerUsage = new CustomerUsage();

        customerUsage.setType( request.getType() );
        customerUsage.setName( request.getName() );
        customerUsage.setValue( request.getValue() );
        customerUsage.setProportion( request.getProportion() );

        return customerUsage;
    }

    @Override
    public CustomerUsageDTO toCustomerUsageDTO(CustomerUsage customerUsage) {
        if ( customerUsage == null ) {
            return null;
        }

        CustomerUsageDTO customerUsageDTO = new CustomerUsageDTO();

        customerUsageDTO.setId( customerUsage.getId() );
        customerUsageDTO.setType( customerUsage.getType() );
        customerUsageDTO.setName( customerUsage.getName() );
        customerUsageDTO.setValue( customerUsage.getValue() );
        customerUsageDTO.setProportion( customerUsage.getProportion() );

        return customerUsageDTO;
    }

    @Override
    public List<CustomerUsageDTO> toCustomerUsageDTOS(List<CustomerUsage> customerUsageList) {
        if ( customerUsageList == null ) {
            return null;
        }

        List<CustomerUsageDTO> list = new ArrayList<CustomerUsageDTO>( customerUsageList.size() );
        for ( CustomerUsage customerUsage : customerUsageList ) {
            list.add( toCustomerUsageDTO( customerUsage ) );
        }

        return list;
    }

    @Override
    public List<ResourceDailyUsageDTO> toResourceDailyUsageDTOS(List<ResourceDailyUsage> resourceDailyUsageList) {
        if ( resourceDailyUsageList == null ) {
            return null;
        }

        List<ResourceDailyUsageDTO> list = new ArrayList<ResourceDailyUsageDTO>( resourceDailyUsageList.size() );
        for ( ResourceDailyUsage resourceDailyUsage : resourceDailyUsageList ) {
            list.add( toResourceDailyUsageDTO( resourceDailyUsage ) );
        }

        return list;
    }

    @Override
    public CustomerUsage toCustomerUsage(ModifyCustomerUsageRequest request) {
        if ( request == null ) {
            return null;
        }

        CustomerUsage customerUsage = new CustomerUsage();

        customerUsage.setId( request.getId() );
        customerUsage.setType( request.getType() );
        customerUsage.setName( request.getName() );
        customerUsage.setValue( request.getValue() );
        customerUsage.setProportion( request.getProportion() );

        return customerUsage;
    }

    @Override
    public ResourceDailyUsage toResourceDailyUsage(ModifyResourceDailyUsageRequest request) {
        if ( request == null ) {
            return null;
        }

        ResourceDailyUsage resourceDailyUsage = new ResourceDailyUsage();

        resourceDailyUsage.setDate( request.getDate() );
        resourceDailyUsage.setType( request.getType() );

        return resourceDailyUsage;
    }

    @Override
    public WorkRunningUsage toWorkRunningUsage(ModifyWorkRunningUsageRequest request) {
        if ( request == null ) {
            return null;
        }

        WorkRunningUsage workRunningUsage = new WorkRunningUsage();

        workRunningUsage.setId( request.getId() );
        workRunningUsage.setName( request.getName() );
        workRunningUsage.setUsername( request.getUsername() );
        workRunningUsage.setCustomerName( request.getCustomerName() );
        workRunningUsage.setWorkUsage( request.getWorkUsage() );
        workRunningUsage.setStatus( request.getStatus() );
        workRunningUsage.setRunningNode( request.getRunningNode() );
        workRunningUsage.setLatitude( request.getLatitude() );
        workRunningUsage.setLongitude( request.getLongitude() );

        return workRunningUsage;
    }

    @Override
    public List<ResourceDailyValueDTO> toResourceDailyValues(List<ResourceDailyValue> values) {
        if ( values == null ) {
            return null;
        }

        List<ResourceDailyValueDTO> list = new ArrayList<ResourceDailyValueDTO>( values.size() );
        for ( ResourceDailyValue resourceDailyValue : values ) {
            list.add( resourceDailyValueToResourceDailyValueDTO( resourceDailyValue ) );
        }

        return list;
    }

    protected ResourceDailyValueDTO resourceDailyValueToResourceDailyValueDTO(ResourceDailyValue resourceDailyValue) {
        if ( resourceDailyValue == null ) {
            return null;
        }

        ResourceDailyValueDTO resourceDailyValueDTO = new ResourceDailyValueDTO();

        resourceDailyValueDTO.setTime( resourceDailyValue.getTime() );
        resourceDailyValueDTO.setNumber( resourceDailyValue.getNumber() );
        resourceDailyValueDTO.setPercentage( resourceDailyValue.getPercentage() );

        return resourceDailyValueDTO;
    }
}
