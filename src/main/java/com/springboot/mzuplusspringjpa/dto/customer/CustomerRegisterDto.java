package com.springboot.mzuplusspringjpa.dto.customer;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomerRegisterDto {
    private String name;
    private String rrnFirst;
    private String rrnLast;
    private String email;
    private String address;
    private String phoneNumber;
}
