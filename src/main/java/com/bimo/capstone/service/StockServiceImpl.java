package com.bimo.capstone.service;

import com.bimo.capstone.domain.Stock;
import com.bimo.capstone.dto.StockDTO;
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
public class StockServiceImpl implements StockService {

    @Autowired
    StockRepository stockRepository;

    @Override
    public List<StockDTO> findAllCustomerStocks() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Tuple> stocks = stockRepository.findAllCustomerStocks(auth.getName());
        return stocks.stream()
                .map(t -> new StockDTO(
                        t.get(0, String.class),
                        t.get(1, String.class)
                        )).collect(Collectors.toList());
    }

    @Override
    public StockDTO findByName(String name) {
        Stock stock = stockRepository.findByName(name);
        return new StockDTO(stock.getName(), stock.getAddress());
    }

    @Override
    public void addStock(StockDTO stock) {
        Stock s = new Stock();
        s.setName(stock.getName());
        s.setAddress(stock.getAddress());
        stockRepository.save(s);
    }

    @Override
    public void deleteStock(int id) {
        Optional<Stock> stock = stockRepository.findById(id);
        stock.ifPresent(value -> stockRepository.delete(value));
    }

    @Override
    public void updateStock(StockDTO stock, int id) {
        Optional<Stock> foundStock = stockRepository.findById(id);
        foundStock.ifPresent(i -> {
            i.setName(stock.getName());
            i.setAddress(stock.getAddress());
            stockRepository.save(i);
        });
    }
}
