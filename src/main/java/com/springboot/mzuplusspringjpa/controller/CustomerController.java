package com.springboot.mzuplusspringjpa.controller;

import com.springboot.mzuplusspringjpa.entity.Customer;
import com.springboot.mzuplusspringjpa.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping("/list")
    public List<Customer> findAll(){
        return customerService.findAll();
    }

    @PostMapping("/save")
    public Customer save(@RequestBody Customer customer){
        return customerService.save(customer);
    }

    @GetMapping("/find/privateInfo")
    public Optional<Customer> findByPrivateInfo(@RequestParam("name") String name,
                                                @RequestParam("rrn_first") String rrnFirst,
                                                @RequestParam("rrn_Last") String rrnLast){
        return customerService.findByPrivateInfo(name,rrnFirst,rrnLast);
    }
}
