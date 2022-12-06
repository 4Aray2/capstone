package com.bimo.capstone.repository;


import com.bimo.capstone.domain.Item;
import com.bimo.capstone.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Integer> {

    List<Stock> findByName(String name);

    @Query(value = "SELECT s.id, s.stock_name, s.address " +
            "FROM item i " +
            "INNER JOIN stock_item si ON i.id = si.item_id " +
            "INNER JOIN stock s ON s.id = si.stock_id " +
            "INNER JOIN customer c ON s.customer_id = c.id " +
            "WHERE c.username = ?1", nativeQuery = true)
    List<Tuple> findAllCustomerStocks(String name);
}
