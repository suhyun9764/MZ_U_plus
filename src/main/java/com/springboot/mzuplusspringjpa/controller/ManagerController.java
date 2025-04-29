package com.springboot.mzuplusspringjpa.controller;

import com.springboot.mzuplusspringjpa.dto.ResponseDto;
import com.springboot.mzuplusspringjpa.dto.manager.ManagerLoginDto;
import com.springboot.mzuplusspringjpa.dto.manager.ManagerRegisterDto;
import com.springboot.mzuplusspringjpa.enums.Result;
import com.springboot.mzuplusspringjpa.service.manager.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Manger API", description = "매니저 관리 API")
public class ManagerController {
    private final ManagerService managerService;

    @Operation(summary = "매니저 로그인 API", responses = {
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "500", description = "로그인 실패"),
    })
    @PostMapping("/managers/login")
    public ResponseEntity<ResponseDto> login(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "로그인할 매니저 정보")
                                             @RequestBody ManagerLoginDto loginDto,
                                             HttpSession session) {
        ResponseDto responseDto = managerService.login(loginDto);
        if (responseDto.getResult().equals(Result.SUCCESS)) {
            session.setAttribute("loginManager",responseDto.getData());
            return ResponseEntity.ok().body(responseDto);
        }

        return ResponseEntity.internalServerError().build();
    }
    @Operation(summary = "매니저 회원가입 API", responses = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공"),
            @ApiResponse(responseCode = "409", description = "이메일 중복"),
            @ApiResponse(responseCode = "500", description = "회원가입 실패"),
    })
    @PostMapping("/managers")
    public ResponseEntity<ResponseDto> register(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "가입할 매니저 정보")
                                                @RequestBody ManagerRegisterDto registerDto) {
        ResponseDto responseDto = managerService.register(registerDto);
        if (responseDto.getResult().equals(Result.SUCCESS))
            return ResponseEntity.ok().body(responseDto);

        if(responseDto.getResult().equals(Result.DUPLICATE))
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        return ResponseEntity.internalServerError().build();
    }
}
