package com.springboot.mzuplusspringjpa.service.customer;

import com.springboot.mzuplusspringjpa.entity.Customer;
import com.springboot.mzuplusspringjpa.repository.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findByPrivateInfo(String name, String rrnFirst, String rrnLast) {
        return customerRepository.findByNameAndRrnFirstAndRrnLast(name,rrnFirst,rrnLast);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
