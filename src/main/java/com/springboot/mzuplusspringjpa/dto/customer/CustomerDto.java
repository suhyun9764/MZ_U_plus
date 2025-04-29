package com.springboot.mzuplusspringjpa.dto.customer;

import com.springboot.mzuplusspringjpa.dto.sale.SaleDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomerDto {
    @Schema(description = "고객 아이디", example = "3")
    private int id;
    @Schema(description = "고객 주소", example = "서울시 송파구")
    private String address;
    @Schema(description = "고객 이메일", example = "test@email.com")
    private String email;
    @Schema(description = "고객 이름", example = "홍길동")
    private String name;
    @Schema(description = "고객 전화번호", example = "01012341234")
    private String phoneNumber;
    @Schema(description = "고객 생년월일", example = "980101")
    private String rrnFirst;
}
