package com.bimo.capstone.service;

import com.bimo.capstone.domain.Item;
import com.bimo.capstone.domain.Stock;
import com.bimo.capstone.domain.StockItem;
import com.bimo.capstone.dto.StockItemDTO;
import com.bimo.capstone.repository.ItemRepository;
import com.bimo.capstone.repository.StockItemRepository;
import com.bimo.capstone.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockItemServiceImpl implements StockItemService {

    @Autowired
    StockItemRepository stockItemRepository;

    @Autowired
    StockRepository stockRepository;

    @Autowired
    ItemRepository itemRepository;


    @Override
    public List<StockItemDTO> findAllCustomerItems() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Tuple> items = stockItemRepository.findAllCustomerItems(auth.getName());
        return items.stream()
                .map(t -> new StockItemDTO(
                        t.get(0, Integer.class),
                        t.get(1, Integer.class),
                        t.get(2, String.class),
                        t.get(3, Integer.class),
                        t.get(4, Integer.class)
                )).collect(Collectors.toList());
    }

    @Override
    public StockItemDTO findByStockIdAndItemId(StockItemDTO item) {
        Optional<Item> foundItem = itemRepository.findById(item.getItemId());
        Optional<Stock> foundStock = stockRepository.findById(item.getStockId());
        if (foundItem.isEmpty() || foundStock.isEmpty()) {
            return null;
        }
        StockItem stockItem = stockItemRepository.findByStockAndItem(foundStock.get(), foundItem.get());
        if (stockItem == null) {
            return null;
        }
        return new StockItemDTO(stockItem.getId(),
                stockItem.getQuantity(),
                stockItem.getLocation(),
                stockItem.getStock().getId(),
                stockItem.getItem().getId());
    }

    @Override
    public void addItem(StockItemDTO item) {
        StockItem stockItem = new StockItem();
        stockItem.setQuantity(item.getQuantity());
        stockItem.setLocation(item.getLocation());
        Optional<Stock> foundStock = stockRepository.findById(item.getStockId());
        Optional<Item> foundItem = itemRepository.findById(item.getItemId());
        foundStock.ifPresent(stockItem::setStock);
        foundItem.ifPresent(stockItem::setItem);
        stockItemRepository.save(stockItem);
    }

    @Override
    public void deleteItem(int id) {
        Optional<StockItem> item = stockItemRepository.findById(id);
        item.ifPresent(value -> stockItemRepository.delete(value));
    }

    @Override
    public void updateItem(StockItemDTO item, int id) {
        Optional<StockItem> foundStockItem = stockItemRepository.findById(id);
        Optional<Stock> foundStock = stockRepository.findById(item.getStockId());
        Optional<Item> foundItem = itemRepository.findById(item.getItemId());
        System.out.println("ok");
        if (foundItem.isPresent() &&
                foundStockItem.isPresent() &&
                foundStock.isPresent()) {
            foundStockItem.get().setQuantity(item.getQuantity());
            foundStockItem.get().setLocation(item.getLocation());
            foundStockItem.get().setItem(foundItem.get());
            foundStockItem.get().setStock(foundStock.get());
            stockItemRepository.save(foundStockItem.get());
        }
    }
}
