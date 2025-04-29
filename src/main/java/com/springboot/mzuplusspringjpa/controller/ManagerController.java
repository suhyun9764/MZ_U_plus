package com.springboot.mzuplusspringjpa.controller;

import com.springboot.mzuplusspringjpa.dto.ResponseDto;
import com.springboot.mzuplusspringjpa.dto.manager.ManagerLoginDto;
import com.springboot.mzuplusspringjpa.dto.manager.ManagerRegisterDto;
import com.springboot.mzuplusspringjpa.enums.Result;
import com.springboot.mzuplusspringjpa.service.manager.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ManagerController {
    private final ManagerService managerService;

    @PostMapping("/manager/login")
    public ResponseEntity<ResponseDto> login(@RequestBody ManagerLoginDto loginDto){
        ResponseDto responseDto = managerService.login(loginDto);
        if(responseDto.getResult().equals(Result.SUCCESS))
            return ResponseEntity.ok().body(responseDto);

        return ResponseEntity.internalServerError().build();
    }

    @PostMapping("/manager")
    public ResponseEntity<ResponseDto> register(@RequestBody ManagerRegisterDto registerDto){
        ResponseDto responseDto = managerService.register(registerDto);
        if(responseDto.getResult().equals(Result.SUCCESS))
            return ResponseEntity.ok().body(responseDto);

        return ResponseEntity.internalServerError().build();
    }
}
