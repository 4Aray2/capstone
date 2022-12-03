package com.bimo.capstone.service;

import com.bimo.capstone.domain.Item;

import java.util.List;

public interface StockItemService {
    List<Result> findAllCustomerItems();
    List<Result> findByName(String name);
    List<Result> addItem(AddItem item);
    void deleteItem(Long id);
}
