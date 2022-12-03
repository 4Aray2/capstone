package com.bimo.capstone.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "name cannot be empty")
    @Column(name = "item_name")
    private String name;

    @NotNull(message = "description cannot be empty")
    private String description;

    @JsonManagedReference(value="item-itemInStocks")
    @OneToMany(mappedBy = "item")
    private List<StockItem> stocksContainingItem;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<StockItem> getStocksContainingItem() {
        return stocksContainingItem;
    }

    public void setStocksContainingItem(List<StockItem> stocksContainingItem) {
        this.stocksContainingItem = stocksContainingItem;
    }
}
