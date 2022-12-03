package com.bimo.capstone.repository;

import com.bimo.capstone.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByCustomerName(String name);
    Customer findByEmail(String email);
    Customer findByPhoneNumber(String number);
    Customer findByUsername(String username);
}
