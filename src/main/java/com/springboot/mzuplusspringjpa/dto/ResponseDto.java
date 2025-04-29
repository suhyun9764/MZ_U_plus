package com.springboot.mzuplusspringjpa.dto;

import com.springboot.mzuplusspringjpa.dto.manager.ManagerLoginDto;
import com.springboot.mzuplusspringjpa.enums.Result;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseDto {
    @Schema(name = "요청 결과", example = "SUCCESS")
    private Result result;
    @Schema(name = "요청 데이터",  example = "{ \"name\": \"홍길동\", \"phoneNumber\": \"01012341234\" }")
    private Object data;
}
