package com.example.yummy.service;

import com.example.yummy.dto.CustomerRequest;
import com.example.yummy.dto.UpdateCustomerRequest;
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

    public String updateCustomer(UpdateCustomerRequest request, String email) {
        Customer customer = repo.findByEmail(email);

        if (customer == null) {
            return "Customer Not Found";
        }

        StringBuilder updatedFields = new StringBuilder();


        if (request.getFirstName() != null) {
            customer.setFirstName(request.getFirstName());
            updatedFields.append("Name, ");
        }
        if (request.getLastName() != null) {
            customer.setLastName(request.getLastName());
            updatedFields.append("Last Name, ");
        }
        if (request.getAddress() != null) {
            customer.setAddress(request.getAddress());
            updatedFields.append("Address, ");
        }
        if (request.getCity() != null) {
            customer.setCity(request.getCity());
            updatedFields.append("City, ");
        }
        if (request.getPincode() != null) {
            customer.setPincode(Integer.parseInt(request.getPincode()));
            updatedFields.append("Pincode, ");
        }
        if (request.getPassword() != null) {
            customer.setPassword(passwordEncoder.encode(request.getPassword()));
            updatedFields.append("Password, ");
        }

        if (updatedFields.length() > 0) {
            updatedFields.setLength(updatedFields.length() - 2);
        }

        repo.save(customer);

        if (updatedFields.length() > 0) {
            return "Customer " + updatedFields.toString() + " updated successfully";
        } else {
            return "No fields were updated";
        }
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