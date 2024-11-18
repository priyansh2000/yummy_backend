package com.priyansh.yummy.mapper;

import com.priyansh.yummy.dto.CustomerRequest;
import com.priyansh.yummy.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toEntity(CustomerRequest request) {
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password())
                .address(request.address())
                .city(request.city())
                .pincode(Integer.parseInt(request.pincode()))
                .build();
    }
}