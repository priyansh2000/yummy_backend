package com.example.yummy.service;

import com.example.yummy.dto.CustomerRequest;
import com.example.yummy.entity.Customer;
import com.example.yummy.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.yummy.repo.CustomerRepo;
import com.example.yummy.dto.CustomerRequest;

@Service
@RequiredArgsConstructor

public class CustomerService {
    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);

        repo.save(customer);
        return "Created";
    }
}

