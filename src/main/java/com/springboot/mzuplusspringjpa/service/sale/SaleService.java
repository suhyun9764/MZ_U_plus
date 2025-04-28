package com.springboot.mzuplusspringjpa.service.sale;

import com.springboot.mzuplusspringjpa.dto.sale.SaleRegisterDto;
import com.springboot.mzuplusspringjpa.dto.ResponseDto;

public interface SaleService {
    ResponseDto register(SaleRegisterDto registerDto);

    ResponseDto list();
}
