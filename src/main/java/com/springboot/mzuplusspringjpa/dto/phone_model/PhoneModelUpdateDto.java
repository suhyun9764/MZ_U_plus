package com.springboot.mzuplusspringjpa.dto.phone_model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PhoneModelUpdateDto {
    private int id;
    private String manufacturer;
    private String name;
    private String color;
    private int stockQuantity;
    private int storageCapacity;
    private int price;
}
