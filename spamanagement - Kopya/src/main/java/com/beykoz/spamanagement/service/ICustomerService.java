package com.beykoz.spamanagement.service;

import com.beykoz.spamanagement.entity.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> getAllCustomers();

    Customer findById(Long id);

    Customer create(Customer customer);

    void delete(Customer customer);

    Customer update(Customer customer);
}
