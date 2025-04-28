package com.springboot.mzuplusspringjpa.dto.sale;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SaleRegisterDto {
    private int customerId;
    private int phoneModelId;
}
