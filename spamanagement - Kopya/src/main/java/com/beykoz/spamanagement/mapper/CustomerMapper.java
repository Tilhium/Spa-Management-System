package com.beykoz.spamanagement.mapper;

import com.beykoz.spamanagement.dto.CustomerDto;
import com.beykoz.spamanagement.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AppointmentMapper.class})
public interface CustomerMapper {

    CustomerDto toDto(Customer customer);

    List<CustomerDto> toDtoList(List<Customer> customers);
}
