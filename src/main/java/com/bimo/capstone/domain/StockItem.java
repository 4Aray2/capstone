package com.bimo.capstone.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "stock_item")
public class StockItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "quantity cannot be empty")
    private int quantity;

    @NotNull(message = "location cannot be empty")
    @Column(name = "item_location")
    private String location;

    @JsonBackReference(value="item-itemInStocks")
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @JsonBackReference(value="stock-itemInStocks")
    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
