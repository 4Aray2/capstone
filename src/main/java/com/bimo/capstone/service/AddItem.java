package com.bimo.capstone.service;

public class AddItem {
    private String itemName;
    private String description;
    private int quantity;
    private String location;
    private long stockId;

    public AddItem(String itemName, String description, int quantity, String location, long stockId) {
        this.itemName = itemName;
        this.description = description;
        this.quantity = quantity;
        this.location = location;
        this.stockId = stockId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public long getStockId() {
        return stockId;
    }

    public void setStockId(long stockId) {
        this.stockId = stockId;
    }
}
