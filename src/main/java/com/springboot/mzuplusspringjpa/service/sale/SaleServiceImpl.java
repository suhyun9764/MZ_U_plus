package com.springboot.mzuplusspringjpa.service.sale;

import com.springboot.mzuplusspringjpa.dto.sale.SaleRegisterDto;
import com.springboot.mzuplusspringjpa.dto.ResponseDto;
import com.springboot.mzuplusspringjpa.dto.sale.SaleDto;
import com.springboot.mzuplusspringjpa.entity.Customer;
import com.springboot.mzuplusspringjpa.entity.PhoneModel;
import com.springboot.mzuplusspringjpa.entity.PhoneSale;
import com.springboot.mzuplusspringjpa.enums.Result;
import com.springboot.mzuplusspringjpa.repository.customer.CustomerRepository;
import com.springboot.mzuplusspringjpa.repository.phone_model.PhoneModelRepository;
import com.springboot.mzuplusspringjpa.repository.sale.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;
    private final PhoneModelRepository phoneModelRepository;

    @Override
    @Transactional
    public ResponseDto register(SaleRegisterDto registerDto) {
        Result result;
        try {
            Customer customer = customerRepository.findById(registerDto.getCustomerId())
                    .orElseThrow(IllegalArgumentException::new);

            PhoneModel phoneModel = phoneModelRepository.findById(registerDto.getPhoneModelId())
                    .orElseThrow(IllegalArgumentException::new);

            phoneModel.decreaseStock();

            PhoneSale phoneSale = PhoneSale.builder()
                    .customer(customer)
                    .phoneModel(phoneModel)
                    .build();

            saleRepository.save(phoneSale);
            result = Result.SUCCESS;
        } catch (Exception e) {
            result = Result.FAIL;
        }
        return ResponseDto.builder()
                .result(result)
                .build();
    }

    @Override
    public ResponseDto list() {
        ResponseDto responseDto;
        try {
            List<SaleDto> saleDtoList = saleRepository.list();
            responseDto = ResponseDto.builder()
                    .data(saleDtoList)
                    .result(Result.SUCCESS)
                    .build();
        } catch (Exception e) {
            responseDto = ResponseDto.builder()
                    .result(Result.FAIL)
                    .build();
        }

        return responseDto;
    }
}
