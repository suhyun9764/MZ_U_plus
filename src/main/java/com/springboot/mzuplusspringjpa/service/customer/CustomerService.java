package com.springboot.mzuplusspringjpa.service.customer;

import com.springboot.mzuplusspringjpa.dto.ResponseDto;
import com.springboot.mzuplusspringjpa.dto.customer.CustomerRegisterDto;
import com.springboot.mzuplusspringjpa.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    ResponseDto findAll();
    ResponseDto findByPrivateInfo(String name, String rrnFirst, String rrnLast);
    ResponseDto save(CustomerRegisterDto dto);
}
