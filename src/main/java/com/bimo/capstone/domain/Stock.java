package com.bimo.capstone.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "name cannot be empty")
    @Column(name = "stock_name")
    private String name;

    @NotNull(message = "address cannot be empty")
    private String address;

    @JsonManagedReference(value="stock-itemInStocks")
    @OneToMany(mappedBy = "stock")
    private List<StockItem> itemsInStock;

    @JsonBackReference(value="customer-stocks")
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<StockItem> getItemsInStock() {
        return itemsInStock;
    }

    public void setItemsInStock(List<StockItem> itemsInStock) {
        this.itemsInStock = itemsInStock;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
