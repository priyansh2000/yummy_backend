package com.example.yummy.service;

import com.example.yummy.dto.CustomerRequest;
import com.example.yummy.entity.Customer;
import com.example.yummy.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.yummy.repo.CustomerRepo;
import com.example.yummy.dto.CustomerRequest;

@Service
@RequiredArgsConstructor

public class CustomerService {
    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        repo.save(customer);
        return "Created";
    }
}

