package com.springboot.mzuplusspringjpa.dto.manager;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class ManagerDto {
    private int id;
    private String name;
    private String email;
    private Map<Integer,String> roles;
}
