package com.springboot.mzuplusspringjpa.dto.manager;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ManagerRegisterDto {
    @Schema(description = "매니저 이메일", example = "manager@email.com")
    private String email;
    @Schema(description = "매니저 패스워드", example = "password")
    private String password;
    @Schema(description = "매니저 이름", example = "홍길동")
    private String name;
}
