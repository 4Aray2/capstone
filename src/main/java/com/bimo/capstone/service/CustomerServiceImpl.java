package com.bimo.capstone.service;

import com.bimo.capstone.domain.Customer;
import com.bimo.capstone.domain.CustomerDetails;
import com.bimo.capstone.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements UserDetailsService, CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username);

        if (customer == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomerDetails(customer);
    }

    @Override
    public boolean isValidLogin(Customer customer, UserDetails foundCustomer) {
        return customer.getUsername().equals(foundCustomer.getUsername()) &&
                bCryptPasswordEncoder.matches(customer.getPassword(), foundCustomer.getPassword());
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void encrypt(Customer customer) {
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
    }

    @Override
    public String getErrorMessage(Customer customer) {
        if (customerRepository.findByCustomerName(customer.getCustomerName()) != null) {
            return "Customer name is already in use";
        } else if (customerRepository.findByPhoneNumber(customer.getPhoneNumber()) != null) {
            return "Phone number is already in use";
        } else if (customerRepository.findByUsername(customer.getUsername()) != null) {
            return "Username is already in use";
        } else if (customerRepository.findByEmail(customer.getEmail()) != null) {
            return "Email is already in use";
        }
        return null;
    }
}