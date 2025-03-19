package com.beykoz.spamanagement.controller;

import com.beykoz.spamanagement.dto.CustomerDto;
import com.beykoz.spamanagement.mapper.CustomerMapper;
import com.beykoz.spamanagement.entity.Customer;
import com.beykoz.spamanagement.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final ICustomerService customerService;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerController(ICustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @GetMapping("/list")
    public ResponseEntity<List<CustomerDto>> getCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerDto> dtos = customerMapper.toDtoList(customers);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findById(@PathVariable("id") Long id) {
        Customer customer = customerService.findById(id);
        CustomerDto dto = customerMapper.toDto(customer);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        customerService.delete(customer);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody Customer customer) {
        Customer created = customerService.create(customer);
        CustomerDto dto = customerMapper.toDto(created);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody Customer customer) {
        Customer updated = customerService.update(customer);
        CustomerDto dto = customerMapper.toDto(updated);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
