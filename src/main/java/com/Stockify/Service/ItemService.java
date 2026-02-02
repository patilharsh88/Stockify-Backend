package com.Stockify.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Stockify.Entity.Item;
import com.Stockify.Exception.ResourceNotFoundException;
import com.Stockify.Repository.ItemRepository;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item createItem(Item item) {
        if (item.getQuantity() == null) {
            item.setQuantity(0);
        }
        return itemRepository.save(item);
    }

    public Item getItem(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Item not found with id: " + id));
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item updateItem(Long id, Item updatedItem) {

        Item item = getItem(id);

        item.setName(updatedItem.getName());
        item.setDescription(updatedItem.getDescription());
        item.setPrice(updatedItem.getPrice());

        return itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        itemRepository.delete(getItem(id));
    }
}
