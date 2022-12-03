package com.bimo.capstone.service;

import com.bimo.capstone.domain.Item;
import com.bimo.capstone.domain.Stock;
import com.bimo.capstone.domain.StockItem;
import com.bimo.capstone.repository.ItemRepository;
import com.bimo.capstone.repository.StockItemRepository;
import com.bimo.capstone.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockItemServiceImpl implements StockItemService {

    @Autowired
    private StockItemRepository stockItemRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<Result> findAllCustomerItems() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return stockItemRepository.findCustomerAllItems(auth.getName());
    }

    @Override
    public List<Result> findByName(String name) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return stockItemRepository.findCustomerItemByName(auth.getName(), name);
    }

    @Override
    public List<Result> addItem(AddItem addItem) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(addItem.getDescription());

        Item item = new Item();
        item.setName(addItem.getItemName());
        item.setDescription(addItem.getDescription());
        itemRepository.save(item);

        StockItem stockItem = new StockItem();
        stockItem.setItem(item);
        stockItem.setQuantity(addItem.getQuantity());
        stockItem.setLocation(addItem.getLocation());
        Optional<Stock> stock = stockRepository.findById(addItem.getStockId());
        if (stock.isEmpty()) {
            stockItem.setStock(null);
        } else {
            stockItem.setStock(stock.get());
        }
        stockItemRepository.save(stockItem);
        return stockItemRepository.findCustomerItemByName(auth.getName(), addItem.getItemName());
    }

    @Override
    public void deleteItem(Long id) {
        Optional<StockItem> stockItem = stockItemRepository.findById(id);
        stockItem.ifPresent(item -> stockItemRepository.delete(item));
    }
}
