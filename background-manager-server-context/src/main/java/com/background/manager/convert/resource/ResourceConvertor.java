package com.background.manager.convert.resource;

import com.background.manager.model.dto.request.resource.*;
import com.background.manager.model.dto.response.resource.*;
import com.background.manager.model.resource.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResourceConvertor {

    ClusterResource toResource(AddResourceRequest request);

    ResourceDigestDTO toResourceDigestDTO(ClusterResource resource);

    List<ResourceDigestDTO> toResourceDigestDTOS(List<ClusterResource> records);

    ClusterResource toResource(ModifyResourceRequest request);

    ResourceDailyUsage toResourceDailyUsage(AddResourceDailyUsage request);

    ResourceDailyUsageDTO toResourceDailyUsageDTO(ResourceDailyUsage resourceDailyUsage);

    WorkRunningUsage toWorkRunningUsage(AddWorkRunningUsageRequest request);

    WorkRunningUsageDTO toWorkRunningUsageDTO(WorkRunningUsage workRunningUsage);

    List<WorkRunningUsageDTO> toWorkRunningUsageDTOList(List<WorkRunningUsage> workRunningUsageList);

    CustomerUsage toCustomerUsage(AddCustomerUsageRequest request);

    CustomerUsageDTO toCustomerUsageDTO(CustomerUsage customerUsage);

    List<CustomerUsageDTO> toCustomerUsageDTOS(List<CustomerUsage> customerUsageList);

    List<ResourceDailyUsageDTO> toResourceDailyUsageDTOS(List<ResourceDailyUsage> resourceDailyUsageList);

    CustomerUsage toCustomerUsage(ModifyCustomerUsageRequest request);

    ResourceDailyUsage toResourceDailyUsage(ModifyResourceDailyUsageRequest request);

    WorkRunningUsage toWorkRunningUsage(ModifyWorkRunningUsageRequest request);

    List<ResourceDailyValueDTO> toResourceDailyValues(List<ResourceDailyValue> values);
}
