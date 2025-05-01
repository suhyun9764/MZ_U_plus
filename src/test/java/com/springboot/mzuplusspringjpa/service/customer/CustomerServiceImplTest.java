package com.springboot.mzuplusspringjpa.service.customer;

import com.springboot.mzuplusspringjpa.dto.ResponseDto;
import com.springboot.mzuplusspringjpa.dto.customer.CustomerDto;
import com.springboot.mzuplusspringjpa.dto.customer.CustomerRegisterDto;
import com.springboot.mzuplusspringjpa.dto.customer.PhoneNumberChangeRequestDto;
import com.springboot.mzuplusspringjpa.dto.customer.PhoneNumberResponseDto;
import com.springboot.mzuplusspringjpa.entity.Customer;
import com.springboot.mzuplusspringjpa.enums.Result;
import com.springboot.mzuplusspringjpa.repository.customer.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @InjectMocks

    private CustomerServiceImpl customerService;
    @Mock
    private CustomerRepository customerRepository;

    Customer customer1 = Customer.builder()
            .name("테스트1")
            .phoneNumber("01011111111")
            .build();
    Customer customer2 = Customer.builder()
            .name("테스트2")
            .phoneNumber("01022222222")
            .build();

    @Test
    void findAll() {
        //given
        when(customerRepository.findAll()).thenReturn(java.util.List.of(customer1, customer2));
        //when
        ResponseDto responseDto = customerService.findAll();
        //then
        assertEquals(Result.SUCCESS, responseDto.getResult());
        java.util.List<CustomerDto> data = (List<CustomerDto>) responseDto.getData();
        assertEquals(2, data.size());
    }

    @Test
    void findByPrivateInfo() {
        //given
        String name = "테스트";
        String rrnFirst = "123456";
        String rrnLast = "1234567";
        when(customerRepository.findByNameAndRrnFirstAndRrnLast(name, rrnFirst, rrnLast))
                .thenReturn(Optional.of(Customer.builder()
                        .name(name)
                        .rrnFirst(rrnFirst)
                        .rrnLast(rrnLast)
                        .build()));
        //when
        ResponseDto responseDto = customerService.findByPrivateInfo(name, rrnFirst, rrnLast);
        //then
        CustomerDto data = (CustomerDto) responseDto.getData();
        assertAll(() -> assertEquals(Result.SUCCESS, responseDto.getResult()),
                () -> assertNotNull(data),
                () -> assertEquals(name, data.getName())
        );


    }

    @Test
    void save() {
        //given
        String name = "테스트";
        String rrnFirst = "123456";
        String rrnLast = "1234567";
        CustomerRegisterDto dto = CustomerRegisterDto.builder()
                .name(name)
                .rrnFirst(rrnFirst)
                .rrnLast(rrnLast)
                .build();

        ResponseDto responseDto = customerService.save(dto);
        //then
        assertEquals(Result.SUCCESS, responseDto.getResult());
    }

    @Test
    void generateNumber() {
        //given
        String last4Digits = "1234";
        when(customerRepository.existsCustomerByPhoneNumber(anyString())).thenReturn(false);
        //when
        ResponseDto responseDto = customerService.generateNumber(last4Digits);
        //then
        PhoneNumberResponseDto data = (PhoneNumberResponseDto) responseDto.getData();
        String phoneNumber = data.getPhoneNumber();
        assertAll(() -> assertEquals(Result.SUCCESS, responseDto.getResult()),
                () -> assertNotNull(data),
                () -> assertTrue(phoneNumber.startsWith("010")),
                () -> assertTrue(phoneNumber.endsWith(last4Digits)),
                () -> assertEquals(11, phoneNumber.length())
        );
    }

    @Test
    void registerNewPhoneNumber() {
        //given
        String newPhoneNumber = "01012341234";
        int id = 1;
        PhoneNumberChangeRequestDto dto = PhoneNumberChangeRequestDto.builder()
                .customerId(1)
                .phoneNumber(newPhoneNumber)
                .build();
        //when
        ResponseDto responseDto = customerService.registerNewPhoneNumber(dto);
        //then
        assertEquals(Result.SUCCESS, responseDto.getResult());
    }
}