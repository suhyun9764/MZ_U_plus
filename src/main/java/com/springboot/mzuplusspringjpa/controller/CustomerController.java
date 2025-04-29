package com.springboot.mzuplusspringjpa.controller;

import com.springboot.mzuplusspringjpa.dto.ResponseDto;
import com.springboot.mzuplusspringjpa.dto.customer.CustomerRegisterDto;
import com.springboot.mzuplusspringjpa.entity.Customer;
import com.springboot.mzuplusspringjpa.enums.Result;
import com.springboot.mzuplusspringjpa.service.customer.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Customer API", description = "고객 관리 하는 API")
public class CustomerController {
    private final CustomerService customerService;

    @Operation(summary = "고객 전체 조회", description = "모든 고객의 정보를 조회합니다", responses = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/customers")
    public ResponseEntity<ResponseDto> findAll() {
        ResponseDto responseDto = customerService.findAll();
        if (responseDto.getResult().equals(Result.SUCCESS))
            return ResponseEntity.ok().body(responseDto);

        return ResponseEntity.internalServerError().build();
    }

    @Operation(summary = "고객 저장", description = "신규 고객을 저장합니다", responses = {
            @ApiResponse(responseCode = "200", description = "저장 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")}
    )
    @PostMapping("/customers")
    public ResponseEntity<ResponseDto> save(@RequestBody CustomerRegisterDto dto) {
        ResponseDto responseDto = customerService.save(dto);
        if (responseDto.getResult().equals(Result.SUCCESS))
            return ResponseEntity.ok(responseDto);

        return ResponseEntity.internalServerError().build();
    }

    @Operation(summary = "개별 고객 조회", description = "고객의 개인정보(이름, 주민번호)로 해당 고객을 조회합니다", responses = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "고객 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")}
    )
    @GetMapping("/customers/private-info")
    public ResponseEntity<ResponseDto> findByPrivateInfo(@Parameter(description = "고객 이름", example = "홍길동")
                                                         @RequestParam("name") String name,
                                                         @Parameter(description = "주민번호 앞자리", example = "980101")
                                                         @RequestParam("rrn_first") String rrnFirst,
                                                         @Parameter(description = "주민번호 뒷자리", example = "11111111")
                                                         @RequestParam("rrn_Last") String rrnLast) {
        ResponseDto responseDto = customerService.findByPrivateInfo(name, rrnFirst, rrnLast);
        if (responseDto.getResult().equals(Result.SUCCESS))
            return ResponseEntity.ok().body(responseDto);

        if (responseDto.getResult().equals(Result.NOT_FOUND))
            return ResponseEntity.notFound().build();

        return ResponseEntity.internalServerError().build();
    }
}
