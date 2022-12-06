package com.bimo.capstone.controller;

import com.bimo.capstone.dto.StockDTO;
import com.bimo.capstone.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class StockController {

    @Autowired
    StockService stockService;

    @GetMapping("/stocks/all")
    public List<StockDTO> list() {
        return stockService.findAllCustomerStocks();
    }

    @GetMapping("/stocks/{name}")
    public List<StockDTO> findByName(@PathVariable String name) {
        return stockService.findByName(name);
    }

    @PutMapping("/stocks")
    public void addStock(@RequestBody StockDTO stock) {
        stockService.addStock(stock);
    }

    @DeleteMapping("/stocks/{id}")
    public void deleteStock(@PathVariable int id) {
        stockService.deleteStock(id);
    }

    @PostMapping("/stocks/{id}")
    public void updateStock(@RequestBody StockDTO stock, @PathVariable int id) {
        stockService.updateStock(stock, id);
    }

}
