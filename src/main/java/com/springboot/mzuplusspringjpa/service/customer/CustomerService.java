package com.springboot.mzuplusspringjpa.service.customer;

import com.springboot.mzuplusspringjpa.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> findAll();
    Optional<Customer> findByPrivateInfo(String name, String rrnFirst, String rrnLast);
    Customer save(Customer customer);
}
