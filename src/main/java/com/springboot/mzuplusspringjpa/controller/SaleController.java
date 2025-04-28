package com.springboot.mzuplusspringjpa.controller;

import com.springboot.mzuplusspringjpa.dto.ResponseDto;
import com.springboot.mzuplusspringjpa.dto.sale.SaleRegisterDto;
import com.springboot.mzuplusspringjpa.service.sale.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sale")
public class SaleController {
    private final SaleService saleService;

    @PostMapping("/register")
    public ResponseDto register(@RequestBody SaleRegisterDto registerDto){
        return saleService.register(registerDto);
    }

    @GetMapping("/list")
    public ResponseDto list(){
        return saleService.list();
    }
}
