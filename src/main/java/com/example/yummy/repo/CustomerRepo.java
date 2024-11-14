package com.example.yummy.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.yummy.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
}
