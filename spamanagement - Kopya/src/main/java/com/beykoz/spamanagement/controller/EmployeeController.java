package com.beykoz.spamanagement.controller;

import com.beykoz.spamanagement.dto.EmployeeDto;
import com.beykoz.spamanagement.entity.Employee;
import com.beykoz.spamanagement.mapper.EmployeeMapper;
import com.beykoz.spamanagement.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final IEmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeController(IEmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    /**
     * Get all employees.
     */
    @GetMapping("/list")
    public ResponseEntity<List<EmployeeDto>> listEmployees(@RequestParam(required = false) Long spaId) {
        List<Employee> employees;
        if (spaId != null) {
            employees = employeeService.findBySpaId(spaId);
        } else {
            employees = employeeService.getAllEmployees();
        }
        return ResponseEntity.ok(employeeMapper.toDtoList(employees));
    }

    /**
     * Get a single employee by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        EmployeeDto dto = employeeMapper.toDto(employee);
        return ResponseEntity.ok(dto);
    }

    /**
     * Create a new employee.
     */
    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.create(employee);
        EmployeeDto dto = employeeMapper.toDto(createdEmployee);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    /**
     * Update an existing employee.
     */
    @PutMapping("/update")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.update(employee);
        EmployeeDto dto = employeeMapper.toDto(updatedEmployee);
        return ResponseEntity.ok(dto);
    }

    /**
     * Delete an employee by ID.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        employeeService.delete(employee);
        return ResponseEntity.noContent().build();
    }
}
