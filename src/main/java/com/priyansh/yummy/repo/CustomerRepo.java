package com.priyansh.yummy.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.priyansh.yummy.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
}
