package com.springboot.mzuplusspringjpa.service.customer;

import com.springboot.mzuplusspringjpa.dto.ResponseDto;
import com.springboot.mzuplusspringjpa.dto.customer.CustomerDto;
import com.springboot.mzuplusspringjpa.dto.customer.CustomerRegisterDto;
import com.springboot.mzuplusspringjpa.dto.customer.PhoneNumberChangeRequestDto;
import com.springboot.mzuplusspringjpa.dto.customer.PhoneNumberResponseDto;
import com.springboot.mzuplusspringjpa.entity.Customer;
import com.springboot.mzuplusspringjpa.enums.Result;
import com.springboot.mzuplusspringjpa.repository.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public ResponseDto findAll() {
        ResponseDto responseDto;
        try {
            List<CustomerDto> customerDtoList = new ArrayList<>();
            List<Customer> customerList = customerRepository.findAll();
            customerList.forEach(customer -> {
                customerDtoList.add(CustomerDto.builder()
                        .name(customer.getName())
                        .id(customer.getId())
                        .email(customer.getEmail())
                        .address(customer.getAddress())
                        .build());
            });
            responseDto = ResponseDto.builder()
                    .result(Result.SUCCESS)
                    .data(customerDtoList)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            responseDto = ResponseDto.builder()
                    .result(Result.FAIL)
                    .build();
        }

        return responseDto;
    }

    @Override
    public ResponseDto findByPrivateInfo(String name, String rrnFirst, String rrnLast) {
        ResponseDto responseDto;
        try {
            Optional<Customer> optionalCustomer = customerRepository.findByNameAndRrnFirstAndRrnLast(name, rrnFirst, rrnLast);
            Customer customer = optionalCustomer.orElseThrow(NoSuchElementException::new);
            CustomerDto customerDto = CustomerDto.builder()
                    .address(customer.getAddress())
                    .name(customer.getName())
                    .phoneNumber(customer.getPhoneNumber())
                    .rrnFirst(customer.getRrnFirst())
                    .email(customer.getEmail())
                    .id(customer.getId())
                    .build();

            responseDto = ResponseDto.builder()
                    .result(Result.SUCCESS)
                    .data(customerDto)
                    .build();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            responseDto = ResponseDto.builder()
                    .result(Result.NOT_FOUND)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            responseDto = ResponseDto.builder()
                    .result(Result.FAIL)
                    .build();
        }

        return responseDto;
    }

    @Override
    @Transactional
    public ResponseDto save(CustomerRegisterDto dto) {
        Result result;
        try {
            Customer customer = Customer.builder()
                    .address(dto.getAddress())
                    .name(dto.getName())
                    .rrnFirst(dto.getRrnFirst())
                    .rrnLast(dto.getRrnLast())
                    .email(dto.getEmail())
                    .phoneNumber(dto.getPhoneNumber())
                    .build();
            customerRepository.save(customer);
            result = Result.SUCCESS;
        } catch (Exception e) {
            result = Result.FAIL;
        }
        return ResponseDto.builder()
                .result(result)
                .build();
    }

    @Override
    public ResponseDto generateNumber(String last4Digits) {
        String phoneNumber = generatePhoneNumber(last4Digits);
        while (customerRepository.existsCustomerByPhoneNumber(phoneNumber)) {
            phoneNumber = generatePhoneNumber(last4Digits);
        }

        return ResponseDto.builder()
                .result(Result.SUCCESS)
                .data(PhoneNumberResponseDto.builder()
                        .phoneNumber(phoneNumber)
                        .build())
                .build();
    }

    @Override
    @Transactional
    public ResponseDto registerNewPhoneNumber(PhoneNumberChangeRequestDto dto) {
        ResponseDto responseDto;
        try {
            customerRepository.registerNewPhoneNumber(dto.getCustomerId(),dto.getPhoneNumber());
            responseDto = ResponseDto.builder()
                    .data(PhoneNumberResponseDto.builder()
                            .phoneNumber(dto.getPhoneNumber())
                            .build())
                    .result(Result.SUCCESS)
                    .build();
        }catch (Exception e){
            responseDto = ResponseDto.builder()
                    .result(Result.FAIL)
                    .build();
        }
        return responseDto;
    }

    private static String generatePhoneNumber(String last4Digits) {
        StringBuilder sb = new StringBuilder();
        sb.append("010");
        int secondPart = (int) (Math.random() * 9000) + 1000;  // 1000 ~ 9999 사이의 숫자
        sb.append(secondPart);
        sb.append(last4Digits);
        String phoneNumber = sb.toString();
        return phoneNumber;
    }
}
