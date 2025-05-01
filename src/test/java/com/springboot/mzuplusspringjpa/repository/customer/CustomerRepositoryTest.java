package com.springboot.mzuplusspringjpa.repository.customer;

import com.springboot.mzuplusspringjpa.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EntityManager em;
    static int customerId;
    @BeforeEach
     void init(){
        Customer customer = Customer.builder()
                .email("test@email.com")
                .name("테스트")
                .rrnFirst("123456")
                .rrnLast("1234567")
                .phoneNumber("01012345678")
                .address("서울시")
                .build();

        Customer savedCustomer = customerRepository.save(customer);
        customerId = savedCustomer.getId();

    }

    @Test
    void findByNameAndRrnFirstAndRrnLast() {
        //given
        String name = "테스트";
        String rrnFirst = "123456";
        String rrnLast = "1234567";
        //when
        Optional<Customer> optionalCustomer = customerRepository.findByNameAndRrnFirstAndRrnLast(name, rrnFirst, rrnLast);
        //then
        assertNotNull(optionalCustomer.get());
    }

    @Test
    void existsCustomerByPhoneNumber() {
        //given
        String phoneNumber = "01012345678";
        //when
        boolean result = customerRepository.existsCustomerByPhoneNumber(phoneNumber);
        //then
        assertTrue(result);
    }

    @Test
    void registerNewPhoneNumber() {

        //given
        String newPhoneNumber = "01011111111";
        //when
        customerRepository.registerNewPhoneNumber(customerId,newPhoneNumber);
        em.flush();
        em.clear();
        //then
        String phoneNumber = customerRepository.findById(customerId).get().getPhoneNumber();
        assertEquals(newPhoneNumber,phoneNumber);
    }
}