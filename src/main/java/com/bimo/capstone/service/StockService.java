package com.bimo.capstone.service;

import com.bimo.capstone.dto.StockDTO;

import java.util.List;

public interface StockService {
    List<StockDTO> findAllCustomerStocks();

    List<StockDTO> findByName(String name);

    void addStock(StockDTO stock);

    void deleteStock(int id);

    void updateStock(StockDTO stock, int id);
}
