package com.springboot.mzuplusspringjpa.controller;

import com.springboot.mzuplusspringjpa.dto.ResponseDto;
import com.springboot.mzuplusspringjpa.dto.sale.SaleRegisterDto;
import com.springboot.mzuplusspringjpa.service.sale.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SaleController {
    private final SaleService saleService;

    @PostMapping("/sale")
    public ResponseDto register(@RequestBody SaleRegisterDto registerDto){
        return saleService.register(registerDto);
    }

    @GetMapping("/sale")
    public ResponseDto list(){
        return saleService.list();
    }
}
