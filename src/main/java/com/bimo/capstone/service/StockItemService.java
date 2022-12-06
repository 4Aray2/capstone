package com.bimo.capstone.service;

import com.bimo.capstone.dto.StockItemDTO;

import java.util.List;

public interface StockItemService {
    List<StockItemDTO> findAllCustomerItems();
    List<StockItemDTO> findByStockIdAndItemId(StockItemDTO item);
    void addItem(StockItemDTO item);
    void deleteItem(int id);
    void updateItem(StockItemDTO item, int id);
}
