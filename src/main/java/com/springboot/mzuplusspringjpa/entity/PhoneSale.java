package com.springboot.mzuplusspringjpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "phone_sales")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @JoinColumn(name = "phone_model_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PhoneModel phoneModel;
}

