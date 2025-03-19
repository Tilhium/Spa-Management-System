package com.beykoz.spamanagement.service;

import com.beykoz.spamanagement.entity.Customer;
import com.beykoz.spamanagement.entity.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> getAllEmployees();

    Employee findById(Long id);

    Employee create(Employee employee);

    void delete(Employee employee);

    Employee update(Employee employee);

    List<Employee> findBySpaId(Long spaId);
}
