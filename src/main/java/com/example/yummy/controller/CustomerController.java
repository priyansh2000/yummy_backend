package com.example.yummy.controller;

import com.example.yummy.auth.JwtUtil;
import com.example.yummy.dto.CustomerRequest;
import com.example.yummy.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v2/customer")
public class CustomerController {

    private final CustomerService customerService;
    private  final JwtUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateCustomer(@RequestHeader("Authorization") String token,
                                                 @RequestBody @Valid CustomerRequest request) {
        if (!jwtUtil.isTokenValid(token, request.email())) {
            return ResponseEntity.status(401).body("Invalid JWT token");
        }
        // Call update service excluding email and password
        return ResponseEntity.ok(customerService.updateCustomer(request));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCustomer(@RequestHeader("Authorization") String token, @RequestBody @Valid CustomerRequest request) {
        if (!jwtUtil.isTokenValid(token, request.email())) {
            return ResponseEntity.status(401).body("Invalid JWT token");
        }
        String email = jwtUtil.extractEmail(token);
        customerService.deleteCustomerByEmail(email);
        return ResponseEntity.ok("Account deleted successfully");
    }
}


