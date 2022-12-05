package com.bimo.capstone.dto;

public class StockItemDTO {
    private int id;
    private int quantity;
    private String location;
    private int itemId;
    private int stockId;

    public StockItemDTO(int id, int quantity, String location, int itemId, int stockId) {
        this.id = id;
        this.quantity = quantity;
        this.location = location;
        this.itemId = itemId;
        this.stockId = stockId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }
}
