package com.bimo.capstone.controller;

import com.bimo.capstone.service.AddItem;
import com.bimo.capstone.service.Result;
import com.bimo.capstone.service.StockItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StockItemController {

    @Autowired
    private StockItemService stockItemService;

    @GetMapping("/product")
    public List<Result> list() {
        return stockItemService.findAllCustomerItems();
    }

    @GetMapping("/search-by-name/{name}")
    public List<Result> findByName(@PathVariable String name) {
        return stockItemService.findByName(name);
    }

    @PostMapping("/product")
    public List<Result> addItem(@RequestBody AddItem item) {
        stockItemService.addItem(item);
        return stockItemService.findByName(item.getItemName());
    }

    @DeleteMapping("/product/{id}")
    public void deleteItem(@PathVariable Long id) {
        stockItemService.deleteItem(id);
    }

    @GetMapping("/home")
    public ResponseEntity<String> home() {
        return new ResponseEntity<>("Home", HttpStatus.OK);
    }

}
