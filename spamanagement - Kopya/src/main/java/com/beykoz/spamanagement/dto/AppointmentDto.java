package com.beykoz.spamanagement.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AppointmentDto {
    private Long id;
    private LocalDateTime appointmentTime;
    private String spaLocation;
    private String employeeName;
    private String employeeExpertise;
    private String customerName;
}
