package com.beykoz.spamanagement.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeDto {
    private Long id;
    private String name;
    private Integer age;
    private Double salary;
    private String expertise;
    private List<AppointmentDto> appointments;
}
