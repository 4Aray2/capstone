package com.bimo.capstone.repository;

import com.bimo.capstone.service.AddItem;
import com.bimo.capstone.service.Result;
import com.bimo.capstone.domain.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockItemRepository extends JpaRepository<StockItem, Long> {

    @Query(value = "SELECT item_name AS itemName, description, si.item_location AS itemLocation, " +
            "si.quantity, s.stock_name AS stockName, s.id AS stockId FROM item i " +
            "INNER JOIN stock_item si  ON i.id = si.item_id " +
            "INNER JOIN stock s ON s.id = si.stock_id " +
            "INNER JOIN customer c ON s.customer_id = c.id " +
            "WHERE c.username = ?1", nativeQuery = true)
    List<Result> findCustomerAllItems(String username);

    @Query(value = "SELECT item_name AS itemName, description, si.item_location AS itemLocation, " +
            "si.quantity, s.stock_name AS stockName, s.id AS stockId FROM item i " +
            "INNER JOIN stock_item si  ON i.id = si.item_id " +
            "INNER JOIN stock s ON s.id = si.stock_id " +
            "INNER JOIN customer c ON s.customer_id = c.id " +
            "WHERE c.username = ?1 " +
            "AND i.item_name = ?2",
            nativeQuery = true)
    List<Result> findCustomerItemByName(String username, String name);
}
