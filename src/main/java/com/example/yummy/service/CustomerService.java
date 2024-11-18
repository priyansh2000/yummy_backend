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
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        repo.save(customer);
        return "Created";
    }

    public String updateCustomer(CustomerRequest request) {
        Customer customer = repo.findByEmail(request.email());
        if (customer == null) {
            return "Customer Not Found";
        }

        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setAddress(request.address());
        customer.setCity(request.city());
        customer.setPincode(Integer.parseInt(request.pincode()));

        repo.save(customer);
        return "Customer updated successfully";
    }

    public String deleteCustomerByEmail(String email) {
        Customer customer = repo.findByEmail(email);
        if (customer != null) {
            repo.delete(customer);
            return "Customer deleted successfully";
        } else {
            return "Customer Not Found";
        }
    }
}
