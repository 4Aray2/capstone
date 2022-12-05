package com.bimo.capstone.service;

import com.bimo.capstone.domain.Item;
import com.bimo.capstone.dto.ItemDTO;
import com.bimo.capstone.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public List<ItemDTO> findAllCustomerItems() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Tuple> items = itemRepository.findAllCustomerItems(auth.getName());
        return items.stream()
                .map(t -> new ItemDTO(
                        t.get(0, Integer.class),
                        t.get(1, String.class),
                        t.get(2, String.class)
                )).collect(Collectors.toList());
    }

    @Override
    public void addItem(ItemDTO item) {
        Item i = new Item();
        i.setName(item.getName());
        i.setDescription(item.getDescription());
        itemRepository.save(i);
    }

    @Override
    public void deleteItem(Integer id) {
        Optional<Item> item = itemRepository.findById(id);
        item.ifPresent(value -> itemRepository.delete(value));
    }

    @Override
    public ItemDTO findByName(String name) {
        Item item = itemRepository.findByName(name);
        return new ItemDTO((int) item.getId(),
                item.getName(),
                item.getDescription());
    }

    @Override
    public void updateItem(ItemDTO item, Integer id) {
        Optional<Item> foundItem = itemRepository.findById(id);
        foundItem.ifPresent(i -> {
            i.setName(item.getName());
            i.setDescription(item.getDescription());
            itemRepository.save(i);
        });
    }
}
