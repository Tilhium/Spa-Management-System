package com.beykoz.spamanagement.controller;

import com.beykoz.spamanagement.dto.AppointmentDto;
import com.beykoz.spamanagement.mapper.AppointmentMapper;
import com.beykoz.spamanagement.entity.Appointment;
import com.beykoz.spamanagement.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final IAppointmentService appointmentService;
    private final AppointmentMapper appointmentMapper;

    @Autowired
    public AppointmentController(IAppointmentService appointmentService, AppointmentMapper appointmentMapper) {
        this.appointmentService = appointmentService;
        this.appointmentMapper = appointmentMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<AppointmentDto> createAppointment(
            @RequestParam Long customerId,
            @RequestParam Long employeeId,
            @RequestParam Long spaId,
            @RequestParam String appointmentTime) {
        LocalDateTime time = LocalDateTime.parse(appointmentTime);
        Appointment appointment = appointmentService.createAppointment(customerId, employeeId, spaId, time);
        AppointmentDto dto = appointmentMapper.toDto(appointment);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<AppointmentDto> updateAppointment(@RequestParam Long appointmentId,
                                                            @RequestParam(required = false) Long employeeId,
                                                            @RequestParam(required = false) Long spaId,
                                                            @RequestParam(required = false) String newTime) {
        LocalDateTime time = newTime != null ? LocalDateTime.parse(newTime) : null;
        Appointment updated = appointmentService.updateAppointment(appointmentId, employeeId, spaId, time);
        AppointmentDto dto = appointmentMapper.toDto(updated);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelAppointment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> getAppointment(@PathVariable Long id) {
        Appointment appointment = appointmentService.findById(id);
        AppointmentDto dto = appointmentMapper.toDto(appointment);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<AppointmentDto>> listAppointments(
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) Long employeeId
    ) {
        List<Appointment> appointments = appointmentService.findAppointments(customerId, employeeId);
        List<AppointmentDto> dtos = appointments.stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

}
