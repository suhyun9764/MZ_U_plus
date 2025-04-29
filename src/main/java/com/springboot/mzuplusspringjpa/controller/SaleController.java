package com.springboot.mzuplusspringjpa.controller;

import com.springboot.mzuplusspringjpa.dto.ResponseDto;
import com.springboot.mzuplusspringjpa.dto.sale.SaleRegisterDto;
import com.springboot.mzuplusspringjpa.enums.Result;
import com.springboot.mzuplusspringjpa.service.sale.SaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Sales API", description = "판매관리 API")
public class SaleController {
    private final SaleService saleService;

    @Operation(summary = "판매 등록 API", responses = {
            @ApiResponse(responseCode = "200", description = "등록 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/sales")
    public ResponseEntity<ResponseDto> register(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "등록할 판매정보")
                                                @RequestBody SaleRegisterDto registerDto) {
        ResponseDto responseDto = saleService.register(registerDto);
        if (responseDto.getResult().equals(Result.SUCCESS))
            return ResponseEntity.ok().body(responseDto);

        return ResponseEntity.internalServerError().build();
    }

    @Operation(summary = "판매 전체 조회 API", responses = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/sales")
    public ResponseEntity<ResponseDto> list() {
        ResponseDto responseDto = saleService.list();
        if (responseDto.getResult().equals(Result.SUCCESS))
            return ResponseEntity.ok().body(responseDto);
        return ResponseEntity.internalServerError().build();
    }
}
