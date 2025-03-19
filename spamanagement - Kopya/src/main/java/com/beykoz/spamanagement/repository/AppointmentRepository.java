package com.beykoz.spamanagement.repository;

import com.beykoz.spamanagement.entity.Appointment;
import com.beykoz.spamanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByEmployeeAndAppointmentTimeBetween(Employee employee, LocalDateTime start, LocalDateTime end);

    Appointment findByEmployeeAndAppointmentTime(Employee employee, LocalDateTime appointmentTime);

    List<Appointment> findByCustomerId(Long customerId);

    List<Appointment> findByEmployeeId(Long employeeId);

    List<Appointment> findByCustomerIdAndEmployeeId(Long customerId, Long employeeId);
}
