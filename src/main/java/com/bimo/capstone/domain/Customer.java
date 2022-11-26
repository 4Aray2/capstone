package com.bimo.capstone.domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "name cannot be empty")
    @Column(name = "customer_name")
    private String customerName;

    @NotNull(message = "email cannot be empty")
    @Column(unique = true)
    private String email;

    @NotNull(message = "phone number cannot be empty")
    @Column(unique = true, name = "phone_number")
    @Length(min = 10, message = "Password should be at least 10 number long")
    private String phoneNumber;

    @NotNull(message = "username cannot be empty")
    @Column(unique = true)
    private String username;

    @NotNull(message = "password cannot be empty")
    @Length(min = 7, message = "Password should be at least 7 characters long")
    private String password;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer")
    private List<Stock> stocks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }
}
