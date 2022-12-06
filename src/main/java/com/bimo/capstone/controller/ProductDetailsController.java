package com.bimo.capstone.controller;

import com.bimo.capstone.dto.StockItemDTO;
import com.bimo.capstone.service.StockItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ProductDetailsController {

    @Autowired
    StockItemService stockItemService;

    @GetMapping("/products-details/all")
    public List<StockItemDTO> list() {
        return stockItemService.findAllCustomerItems();
    }

    @GetMapping("/products-details")
    public List<StockItemDTO> findByStockIdAndItemId(@RequestBody StockItemDTO item) {
        return stockItemService.findByStockIdAndItemId(item);
    }

    @PutMapping("/products-details")
    public void addItem(@RequestBody StockItemDTO item) {
        stockItemService.addItem(item);
    }

    @DeleteMapping("/products-details/{id}")
    public void deleteItem(@PathVariable int id) {
        stockItemService.deleteItem(id);
    }

    //TODO
    @PostMapping("/products-details/{id}")
    public void updateItem(@RequestBody StockItemDTO item, @PathVariable int id) {
        stockItemService.updateItem(item, id);
    }

}
