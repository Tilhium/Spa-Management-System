package com.beykoz.spamanagement.mapper;

import com.beykoz.spamanagement.dto.EmployeeDto;
import com.beykoz.spamanagement.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AppointmentMapper.class})
public interface EmployeeMapper {

    @Mapping(source = "expertise", target = "expertise")
    EmployeeDto toDto(Employee employee);

    List<EmployeeDto> toDtoList(List<Employee> employees);
}
