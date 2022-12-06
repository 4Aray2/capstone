package com.bimo.capstone.service;

import com.bimo.capstone.domain.Customer;
import com.bimo.capstone.domain.Stock;
import com.bimo.capstone.dto.StockDTO;
import com.bimo.capstone.repository.CustomerRepository;
import com.bimo.capstone.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    StockRepository stockRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<StockDTO> findAllCustomerStocks() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Tuple> stocks = stockRepository.findAllCustomerStocks(auth.getName());
        return stocks.stream()
                .map(t -> new StockDTO(
                        t.get(0, Integer.class),
                        t.get(1, String.class),
                        t.get(2, String.class)
                        )).collect(Collectors.toList());
    }

    @Override
    public List<StockDTO> findByName(String name) {
        List<Stock> stocks = stockRepository.findByName(name);
        if (stocks == null) {
            return null;
        }
        List<StockDTO> list = new ArrayList<>();
        for (Stock s : stocks) {
            list.add(new StockDTO(s.getId(), s.getName(), s.getAddress()));
        }
        return list;
    }

    @Override
    public void addStock(StockDTO stock) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Stock s = new Stock();
        s.setName(stock.getName());
        s.setAddress(stock.getAddress());
        s.setCustomer(customerRepository.findByUsername(auth.getName()));
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
