package com.springboot.mzuplusspringjpa.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomerDto {
    private int id;
    private String address;
    private String email;
    private String name;
    private String phoneNumber;
    private String rrnFirst;
}
