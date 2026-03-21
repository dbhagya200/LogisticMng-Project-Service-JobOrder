package lk.ijse.eca.orderservice.mapper;

import lk.ijse.eca.orderservice.dto.CustomerDto;
import lk.ijse.eca.orderservice.dto.JobOrderDto;
import lk.ijse.eca.orderservice.entity.JobOrders;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface JobOrdersMapper {

    @Mapping(target = "customer", source = "customer")
    JobOrderDto toDto(JobOrders orders, CustomerDto customer);

    @Mapping(target = "id", ignore = true)
    JobOrders toEntity(JobOrderDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(JobOrderDto dto, @MappingTarget JobOrders orders);
}
