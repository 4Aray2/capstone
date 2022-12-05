package com.bimo.capstone.service;

import com.bimo.capstone.domain.Item;
import com.bimo.capstone.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    List<ItemDTO> findAllCustomerItems();
    void addItem(ItemDTO item);
    void deleteItem(Integer id);
    ItemDTO findByName(String name);
    void updateItem(ItemDTO item, Integer id);
}
