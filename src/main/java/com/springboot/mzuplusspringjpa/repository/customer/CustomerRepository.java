package com.springboot.mzuplusspringjpa.repository.customer;

import com.springboot.mzuplusspringjpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByNameAndRrnFirstAndRrnLast(String name, String rrnFirst, String rrnLast);
}
