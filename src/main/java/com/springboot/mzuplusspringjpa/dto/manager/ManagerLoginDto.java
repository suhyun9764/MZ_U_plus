package com.springboot.mzuplusspringjpa.dto.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ManagerLoginDto {
    private String email;
    private String password;
}
