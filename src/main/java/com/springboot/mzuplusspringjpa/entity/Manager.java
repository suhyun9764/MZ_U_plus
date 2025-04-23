package com.springboot.mzuplusspringjpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "managers")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;
    private String email;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

}
