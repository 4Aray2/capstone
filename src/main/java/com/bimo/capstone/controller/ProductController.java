package com.bimo.capstone.controller;

import com.bimo.capstone.dto.ItemDTO;
import com.bimo.capstone.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/products/all")
    public List<ItemDTO> list() {
        return itemService.findAllCustomerItems();
    }

    @GetMapping("/products/{name}")
    public ItemDTO findByName(@PathVariable String name) {
        return itemService.findByName(name);
    }

    @PutMapping("/products")
    public void addItem(@RequestBody ItemDTO item) {
        itemService.addItem(item);
    }

    @DeleteMapping("/products/{id}")
    public void deleteItem(@PathVariable int id) {
        itemService.deleteItem(id);
    }

    @PostMapping("/products/{id}")
    public void updateItem(@RequestBody ItemDTO item, @PathVariable int id) {
        itemService.updateItem(item, id);
    }
}
