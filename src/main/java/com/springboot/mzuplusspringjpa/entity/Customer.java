package com.springboot.mzuplusspringjpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers", uniqueConstraints = @UniqueConstraint(columnNames = {"rrn_first","rrn_last"}))
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "rrn_first")
    private String rrnFirst;
    @Column(name = "rrn_last")
    private String rrnLast;
    private String email;
    private String address;
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<PhoneSale> phoneSaleList = new ArrayList<>();
}
