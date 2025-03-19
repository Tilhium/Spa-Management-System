package com.beykoz.spamanagement.service;

import com.beykoz.spamanagement.entity.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {
    Appointment createAppointment(Long customerId, Long employeeId, Long spaId, LocalDateTime appointmentTime);
    Appointment updateAppointment(Long appointmentId, Long newEmployeeId, Long newSpaId, LocalDateTime newTime);
    void cancelAppointment(Long appointmentId);
    Appointment findById(Long id);
    List<Appointment> findAll();
    List<Appointment> findAppointments(Long customerId, Long employeeId);
}
