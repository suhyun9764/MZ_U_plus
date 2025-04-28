package com.springboot.mzuplusspringjpa.dto.sale;

import com.springboot.mzuplusspringjpa.dto.customer.CustomerDto;
import com.springboot.mzuplusspringjpa.dto.phone_model.PhoneModelDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SaleDto {
    private int saleId;
    private CustomerDto customerDto;
    private PhoneModelDto phoneModelDto;
}
