package com.springboot.mzuplusspringjpa.controller;

import com.springboot.mzuplusspringjpa.dto.ResponseDto;
import com.springboot.mzuplusspringjpa.dto.phone_model.PhoneModelDeleteDto;
import com.springboot.mzuplusspringjpa.dto.phone_model.PhoneModelRegisterDto;
import com.springboot.mzuplusspringjpa.dto.phone_model.PhoneModelUpdateDto;
import com.springboot.mzuplusspringjpa.enums.Result;
import com.springboot.mzuplusspringjpa.service.phone_model.PhoneModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/phone-models")
public class PhoneModelController {
    private final PhoneModelService phoneModelservice;
    @GetMapping("")
    public ResponseEntity<ResponseDto> findAll(){
        ResponseDto responseDto = phoneModelservice.findAll();
        if(responseDto.getResult().equals(Result.SUCCESS))
            return ResponseEntity.ok(responseDto);

        return ResponseEntity.internalServerError().build();
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto> register(@RequestBody PhoneModelRegisterDto dto){
        ResponseDto responseDto = phoneModelservice.register(dto);
        if(responseDto.getResult().equals(Result.SUCCESS))
            return ResponseEntity.ok().build();

        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("")
    public ResponseEntity<ResponseDto> update(@RequestBody PhoneModelUpdateDto dto){
        ResponseDto responseDto = phoneModelservice.update(dto);
        if(responseDto.getResult().equals(Result.SUCCESS))
            return ResponseEntity.ok().build();

        return ResponseEntity.internalServerError().build();
    }

    @DeleteMapping("")
    public ResponseEntity<ResponseDto> delete(@RequestBody PhoneModelDeleteDto dto){
        ResponseDto responseDto = phoneModelservice.delete(dto);
        if(responseDto.getResult().equals(Result.SUCCESS))
            return ResponseEntity.ok().build();

        return ResponseEntity.internalServerError().build();
    }
}
