package com.springboot.mzuplusspringjpa.controller;

import com.springboot.mzuplusspringjpa.dto.ResponseDto;
import com.springboot.mzuplusspringjpa.entity.Customer;
import com.springboot.mzuplusspringjpa.enums.Result;
import com.springboot.mzuplusspringjpa.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping("/customer")
    public ResponseEntity<ResponseDto> findAll(){
        ResponseDto responseDto = customerService.findAll();
        if(responseDto.getResult().equals(Result.SUCCESS))
            return ResponseEntity.ok().body(responseDto);

        return ResponseEntity.internalServerError().build();
    }

    @PostMapping("/customer")
    public ResponseEntity<ResponseDto> save(@RequestBody Customer customer){
        ResponseDto responseDto = customerService.save(customer);
        if(responseDto.getResult().equals(Result.SUCCESS))
            return ResponseEntity.ok().build();

        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/customer/private-info")
    public ResponseEntity<ResponseDto> findByPrivateInfo(@RequestParam("name") String name,
                                                @RequestParam("rrn_first") String rrnFirst,
                                                @RequestParam("rrn_Last") String rrnLast){
        ResponseDto responseDto = customerService.findByPrivateInfo(name, rrnFirst, rrnLast);
        if(responseDto.getResult().equals(Result.SUCCESS))
            return ResponseEntity.ok().body(responseDto);

        if(responseDto.getResult().equals(Result.NOT_FOUND))
            return ResponseEntity.notFound().build();

        return ResponseEntity.internalServerError().build();
    }
}
