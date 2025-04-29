package com.springboot.mzuplusspringjpa.service.phone_model;

import com.springboot.mzuplusspringjpa.dto.ResponseDto;
import com.springboot.mzuplusspringjpa.dto.phone_model.PhoneModelDeleteDto;
import com.springboot.mzuplusspringjpa.dto.phone_model.PhoneModelDto;
import com.springboot.mzuplusspringjpa.dto.phone_model.PhoneModelRegisterDto;
import com.springboot.mzuplusspringjpa.dto.phone_model.PhoneModelUpdateDto;
import com.springboot.mzuplusspringjpa.entity.PhoneModel;
import com.springboot.mzuplusspringjpa.enums.Result;
import com.springboot.mzuplusspringjpa.repository.phone_model.PhoneModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PhoneModelServiceImpl implements PhoneModelService {
    private final PhoneModelRepository phoneModelRepository;

    @Override
    public ResponseDto findAll() {
        ResponseDto responseDto;
        try {
            List<PhoneModelDto> phoneModelDtoList = new ArrayList<>();
            List<PhoneModel> phoneModelList = phoneModelRepository.findAll();
            phoneModelList.forEach(phoneModel -> {
                PhoneModelDto phoneModelDto = PhoneModelDto.builder()
                        .id(phoneModel.getId())
                        .name(phoneModel.getName())
                        .color(phoneModel.getColor())
                        .manufacturer(phoneModel.getManufacturer())
                        .stockQuantity(phoneModel.getStockQuantity())
                        .price(phoneModel.getPrice())
                        .storageCapacity(phoneModel.getStorageCapacity())
                        .build();
                phoneModelDtoList.add(phoneModelDto);
            });
            responseDto = ResponseDto.builder()
                    .result(Result.SUCCESS)
                    .data(phoneModelDtoList)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            responseDto = ResponseDto.builder()
                    .result(Result.FAIL)
                    .build();
        }
        return responseDto;
    }

    @Override
    public ResponseDto register(PhoneModelRegisterDto dto) {
        ResponseDto responseDto;
        try {
            PhoneModel phoneModel = PhoneModel.builder()
                    .name(dto.getName())
                    .color(dto.getColor())
                    .manufacturer(dto.getManufacturer())
                    .stockQuantity(dto.getStockQuantity())
                    .storageCapacity(dto.getStorageCapacity())
                    .price(dto.getPrice())
                    .build();

            phoneModelRepository.save(phoneModel);
            responseDto = ResponseDto.builder()
                    .result(Result.SUCCESS)
                    .build();
        }catch (Exception e){
            responseDto = ResponseDto.builder()
                    .result(Result.FAIL)
                    .build();
        }

        return responseDto;
    }

    @Override
    public ResponseDto update(PhoneModelUpdateDto dto) {
        ResponseDto responseDto;
        try {
            PhoneModel phoneModel = PhoneModel.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .color(dto.getColor())
                    .manufacturer(dto.getManufacturer())
                    .stockQuantity(dto.getStockQuantity())
                    .storageCapacity(dto.getStorageCapacity())
                    .price(dto.getPrice())
                    .build();

            phoneModelRepository.save(phoneModel);
            responseDto = ResponseDto.builder()
                    .result(Result.SUCCESS)
                    .build();
        }catch (Exception e){
            responseDto = ResponseDto.builder()
                    .result(Result.FAIL)
                    .build();
        }

        return responseDto;
    }

    @Override
    public ResponseDto delete(PhoneModelDeleteDto dto) {
        ResponseDto responseDto;
        try {
            phoneModelRepository.deleteById(dto.getId());
            responseDto = ResponseDto.builder()
                    .result(Result.SUCCESS)
                    .build();
        }catch (Exception e){
            responseDto = ResponseDto.builder()
                    .result(Result.FAIL)
                    .build();
        }

        return responseDto;
    }
}
