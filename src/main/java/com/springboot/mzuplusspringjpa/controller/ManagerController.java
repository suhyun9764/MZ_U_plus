package com.springboot.mzuplusspringjpa.controller;

import com.springboot.mzuplusspringjpa.dto.ResponseDto;
import com.springboot.mzuplusspringjpa.dto.manager.ManagerLoginDto;
import com.springboot.mzuplusspringjpa.dto.manager.ManagerRegisterDto;
import com.springboot.mzuplusspringjpa.service.manager.ManagerService;
import lombok.RequiredArgsConstructor;
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
    public ResponseDto login(@RequestBody ManagerLoginDto loginDto){
        return managerService.login(loginDto);
    }

    @PostMapping("/manager")
    public ResponseDto register(@RequestBody ManagerRegisterDto registerDto){
        return managerService.register(registerDto);
    }
}
