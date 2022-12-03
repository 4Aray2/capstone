package com.bimo.capstone.service;

import com.bimo.capstone.domain.Customer;
import org.springframework.security.core.userdetails.UserDetails;

public interface CustomerService {
    void saveCustomer(Customer customer);
    void encrypt(Customer customer);
    String getErrorMessage(Customer customer);
    UserDetails loadUserByUsername(String username);
    boolean isValidLogin(Customer customer, UserDetails foundCustomer);
}
