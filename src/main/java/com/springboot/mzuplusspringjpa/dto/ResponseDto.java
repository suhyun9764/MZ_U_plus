package com.springboot.mzuplusspringjpa.dto;

import com.springboot.mzuplusspringjpa.enums.Result;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseDto {
    private Result result;
    private Object data;
}
