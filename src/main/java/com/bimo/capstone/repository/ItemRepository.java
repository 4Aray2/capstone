package com.bimo.capstone.repository;

import com.bimo.capstone.domain.Item;
import com.bimo.capstone.dto.ItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import javax.transaction.Transactional;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    Item findByName(String name);

    @Query(value = "SELECT i.id, i.item_name, i.description " +
            "FROM item i " +
            "INNER JOIN stock_item si ON i.id = si.item_id " +
            "INNER JOIN stock s ON s.id = si.stock_id " +
            "INNER JOIN customer c ON s.customer_id = c.id " +
            "WHERE c.username = ?1", nativeQuery = true)
    List<Tuple> findAllCustomerItems(String name);
}
