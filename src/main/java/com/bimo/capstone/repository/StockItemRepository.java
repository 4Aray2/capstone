package com.bimo.capstone.repository;

import com.bimo.capstone.domain.Item;
import com.bimo.capstone.domain.Stock;
import com.bimo.capstone.domain.StockItem;
import com.bimo.capstone.dto.StockItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.util.List;

public interface StockItemRepository extends JpaRepository<StockItem, Integer> {

    List<StockItem> findByStockAndItem(Stock stock, Item item);

    @Query(value = "SELECT si.id, si.quantity, si.item_location, si.stock_id, si.item_id " +
            "FROM item i " +
            "INNER JOIN stock_item si ON i.id = si.item_id " +
            "INNER JOIN stock s ON s.id = si.stock_id " +
            "INNER JOIN customer c ON s.customer_id = c.id " +
            "WHERE c.username = ?1", nativeQuery = true)
    List<Tuple> findAllCustomerItems(String name);
}
