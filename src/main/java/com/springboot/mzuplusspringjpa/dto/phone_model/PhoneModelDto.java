package com.springboot.mzuplusspringjpa.dto.phone_model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class PhoneModelDto {
    private int id;
    private String manufacturer;
    private String name;
    private String color;
    private int stockQuantity;
    private int storageCapacity;
}
