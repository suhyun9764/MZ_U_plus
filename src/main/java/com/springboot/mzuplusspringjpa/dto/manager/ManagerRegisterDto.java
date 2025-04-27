package com.springboot.mzuplusspringjpa.dto.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ManagerRegisterDto {
    private String email;
    private String password;
    private String name;
}
