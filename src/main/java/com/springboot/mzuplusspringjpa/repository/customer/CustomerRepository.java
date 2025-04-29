package com.springboot.mzuplusspringjpa.repository.customer;

import com.springboot.mzuplusspringjpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByNameAndRrnFirstAndRrnLast(String name, String rrnFirst, String rrnLast);

    boolean existsCustomerByPhoneNumber(String phoneNumber);
    @Modifying
    @Query("UPDATE Customer c SET c.phoneNumber = :phoneNumber WHERE c.id = :customerId")
    void registerNewPhoneNumber(@Param("customerId") int customerId, @Param("phoneNumber") String phoneNumber);
}
