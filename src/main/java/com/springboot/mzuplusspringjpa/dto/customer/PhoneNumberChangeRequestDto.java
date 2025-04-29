package com.springboot.mzuplusspringjpa.dto.customer;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PhoneNumberChangeRequestDto {
    private int customerId;
    private String phoneNumber;
}
