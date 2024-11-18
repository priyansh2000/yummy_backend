package com.example.yummy.controller;

import com.example.yummy.auth.JwtUtil;
import com.example.yummy.dto.CustomerRequest;
import com.example.yummy.dto.DeleteCustomerRequest;
import com.example.yummy.dto.UpdateCustomerRequest;
import com.example.yummy.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }
    @PutMapping("/update/{email}")
    public ResponseEntity<String> updateCustomer(
            @PathVariable String email,
            @RequestBody @Valid UpdateCustomerRequest request) {
        String response = customerService.updateCustomer(request, email);
        return ResponseEntity.ok(response);
    }
       @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCustomer(@RequestBody @Valid DeleteCustomerRequest request) {
        return ResponseEntity.ok(customerService.deleteCustomerByEmail(request.getEmail()));
    }
}