package com.springboot.mzuplusspringjpa.dto.sale;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SaleRegisterDto {
    @Schema(name = "판매 등록할 고객 ID", example = "3")
    private int customerId;
    @Schema(name = "판매 등록할 폰 모델 ID", example = "1")
    private int phoneModelId;
}
