package com.bimo.capstone.controller;

import com.bimo.capstone.domain.StockItem;
import com.bimo.capstone.repository.StockItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    private StockItemRepository stockItemRepository;

    @GetMapping("/list/all")
    public List<StockItem> list() {
        return stockItemRepository.findAll();
    }

    @GetMapping("/home")
    public void home() {}

}
