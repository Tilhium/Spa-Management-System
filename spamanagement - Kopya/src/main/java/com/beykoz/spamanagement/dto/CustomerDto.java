package com.beykoz.spamanagement.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDto {
    private Long id;
    private String name;
    private Integer age;
    private List<AppointmentDto> appointments;
}
