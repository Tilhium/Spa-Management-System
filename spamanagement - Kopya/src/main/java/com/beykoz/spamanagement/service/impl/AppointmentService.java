package com.beykoz.spamanagement.service.impl;

import com.beykoz.spamanagement.entity.Appointment;
import com.beykoz.spamanagement.entity.Customer;
import com.beykoz.spamanagement.entity.Employee;
import com.beykoz.spamanagement.entity.Spa;
import com.beykoz.spamanagement.repository.AppointmentRepository;
import com.beykoz.spamanagement.repository.CustomerRepository;
import com.beykoz.spamanagement.repository.EmployeeRepository;
import com.beykoz.spamanagement.repository.SpaRepository;
import com.beykoz.spamanagement.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService implements IAppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final SpaRepository spaRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository,
                              CustomerRepository customerRepository,
                              EmployeeRepository employeeRepository,
                              SpaRepository spaRepository) {
        this.appointmentRepository = appointmentRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.spaRepository = spaRepository;
    }

    @Override
    public Appointment createAppointment(Long customerId, Long employeeId, Long spaId, LocalDateTime appointmentTime) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        Spa spa = spaRepository.findById(spaId).orElseThrow(() -> new RuntimeException("Spa not found"));

        // Çakışma kontrolü: aynı employee ve appointmentTime için kayıt var mı?
        if (appointmentRepository.findByEmployeeAndAppointmentTime(employee, appointmentTime) != null) {
            throw new RuntimeException("This employee is not available at that time!");
        }

        Appointment appointment = new Appointment();
        appointment.setCustomer(customer);
        appointment.setEmployee(employee);
        appointment.setSpa(spa);
        appointment.setAppointmentTime(appointmentTime);

        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Long appointmentId, Long newEmployeeId, Long newSpaId, LocalDateTime newTime) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new RuntimeException("Appointment not found"));

        if (newEmployeeId != null) {
            Employee newEmployee = employeeRepository.findById(newEmployeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
            // Yeni employee ile yeni tarih çakışma kontrolü
            if (appointmentRepository.findByEmployeeAndAppointmentTime(newEmployee, newTime) != null) {
                throw new RuntimeException("This employee is not available at that time!");
            }
            appointment.setEmployee(newEmployee);
        }

        if (newSpaId != null) {
            Spa newSpa = spaRepository.findById(newSpaId).orElseThrow(() -> new RuntimeException("Spa not found"));
            appointment.setSpa(newSpa);
        }

        if (newTime != null) {
            // Mevcut employee ile de kontrol etmek gerekebilir
            Employee currentEmployee = appointment.getEmployee();
            if (appointmentRepository.findByEmployeeAndAppointmentTime(currentEmployee, newTime) != null) {
                throw new RuntimeException("This employee is not available at that time!");
            }
            appointment.setAppointmentTime(newTime);
        }

        return appointmentRepository.save(appointment);
    }

    @Override
    public void cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointmentRepository.delete(appointment);
    }

    @Override
    public List<Appointment> findAppointments(Long customerId, Long employeeId) {
        if (customerId != null && employeeId != null) {
            return appointmentRepository.findByCustomerIdAndEmployeeId(customerId, employeeId);
        } else if (customerId != null) {
            return appointmentRepository.findByCustomerId(customerId);
        } else if (employeeId != null) {
            return appointmentRepository.findByEmployeeId(employeeId);
        } else {
            return appointmentRepository.findAll();
        }
    }


    @Override
    public Appointment findById(Long id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }
}
