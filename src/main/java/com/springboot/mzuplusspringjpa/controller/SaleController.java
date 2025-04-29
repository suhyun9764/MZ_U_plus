package com.springboot.mzuplusspringjpa.controller;

import com.springboot.mzuplusspringjpa.dto.ResponseDto;
import com.springboot.mzuplusspringjpa.dto.sale.SaleRegisterDto;
import com.springboot.mzuplusspringjpa.enums.Result;
import com.springboot.mzuplusspringjpa.service.sale.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SaleController {
    private final SaleService saleService;

    @PostMapping("/sale")
    public ResponseEntity<ResponseDto> register(@RequestBody SaleRegisterDto registerDto){
        ResponseDto responseDto = saleService.register(registerDto);
        if(responseDto.getResult().equals(Result.SUCCESS))
            return ResponseEntity.ok().body(responseDto);

        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/sale")
    public ResponseEntity<ResponseDto> list(){
        ResponseDto responseDto = saleService.list();
        if(responseDto.getResult().equals(Result.SUCCESS))
            return ResponseEntity.ok().body(responseDto);
        return ResponseEntity.internalServerError().build();
    }
}
