package com.beykoz.spamanagement.mapper;

import com.beykoz.spamanagement.dto.AppointmentDto;
import com.beykoz.spamanagement.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    @Mapping(source = "spa.location", target = "spaLocation")
    @Mapping(source = "employee.name", target = "employeeName")
    @Mapping(source = "employee.expertise", target = "employeeExpertise")
    @Mapping(source = "customer.name", target = "customerName")
    AppointmentDto toDto(Appointment appointment);
}
