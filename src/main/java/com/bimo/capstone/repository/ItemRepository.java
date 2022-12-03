package com.bimo.capstone.repository;

import com.bimo.capstone.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
