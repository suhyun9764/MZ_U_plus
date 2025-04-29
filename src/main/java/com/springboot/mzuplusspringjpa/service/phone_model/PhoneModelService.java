package com.springboot.mzuplusspringjpa.service.phone_model;

import com.springboot.mzuplusspringjpa.dto.ResponseDto;
import com.springboot.mzuplusspringjpa.dto.phone_model.PhoneModelDeleteDto;
import com.springboot.mzuplusspringjpa.dto.phone_model.PhoneModelRegisterDto;
import com.springboot.mzuplusspringjpa.dto.phone_model.PhoneModelUpdateDto;

public interface PhoneModelService {
    ResponseDto findAll();

    ResponseDto register(PhoneModelRegisterDto dto);

    ResponseDto update(PhoneModelUpdateDto dto);

    ResponseDto delete(PhoneModelDeleteDto dto);
}
