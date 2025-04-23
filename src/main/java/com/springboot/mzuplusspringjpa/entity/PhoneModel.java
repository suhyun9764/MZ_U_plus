package com.springboot.mzuplusspringjpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "phone_models")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String manufacturer;
    private String color;
    @Column(name = "storage_capacity")
    private int storageCapacity;
    private int price;
    @Column(name = "stock_quantity")
    private int stockQuantity;
    @OneToMany(mappedBy = "phoneModel", fetch = FetchType.LAZY)
    private List<PhoneSale> phoneSaleList;
}
