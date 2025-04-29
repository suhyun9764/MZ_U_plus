package com.springboot.mzuplusspringjpa.service.customer;

import com.springboot.mzuplusspringjpa.dto.ResponseDto;
import com.springboot.mzuplusspringjpa.dto.customer.CustomerRegisterDto;
import com.springboot.mzuplusspringjpa.dto.customer.PhoneNumberChangeRequestDto;

public interface CustomerService {
    ResponseDto findAll();
    ResponseDto findByPrivateInfo(String name, String rrnFirst, String rrnLast);
    ResponseDto save(CustomerRegisterDto dto);

    ResponseDto generateNumber(String last4Digits);

    ResponseDto registerNewPhoneNumber(PhoneNumberChangeRequestDto dto);
}
