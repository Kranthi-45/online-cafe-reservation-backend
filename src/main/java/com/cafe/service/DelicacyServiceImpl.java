package com.cafe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cafe.entity.Delicacy;
import com.cafe.exception.ItemCreationException;
import com.cafe.exception.ItemNotFoundException;
import com.cafe.exception.NoItemsFoundException;
import com.cafe.repository.DelicacyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DelicacyServiceImpl implements DelicacyService {
    private DelicacyRepository itemRepository;
        
    @Autowired
    public DelicacyServiceImpl(DelicacyRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Delicacy> getAllItems() {
        List<Delicacy> items = itemRepository.findAll();
        if (items.isEmpty()) {
            throw new NoItemsFoundException("No items found.");
        }
        return items;
    }

    @Override
    public Delicacy getItemById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("Item not found with ID: " + itemId));
    }

    @Override
    public Delicacy createItem(Delicacy item) {
        try {
            return itemRepository.save(item);
        } catch (Exception ex) {
            throw new ItemCreationException("Error creating item: " + ex.getMessage());
        }
    }

    @Override
    public Delicacy updateItem(Long itemId, Delicacy updatedItem) {
        Optional<Delicacy> optionalExistingItem = itemRepository.findById(itemId);
        if (optionalExistingItem.isPresent()) {
            Delicacy existingItem = optionalExistingItem.get();
            // Update non-null properties of existingItem with the corresponding properties from updatedItem
            if (updatedItem.getItemName() != null) {
                existingItem.setItemName(updatedItem.getItemName());
            }
            if (updatedItem.getPrice() != null) {
                existingItem.setPrice(updatedItem.getPrice());
            }
            if (updatedItem.getAvailability() != null) {
                existingItem.setAvailability(updatedItem.getAvailability());
            }
            if (updatedItem.getType() != null) {
                existingItem.setType(updatedItem.getType());
            }
            if (updatedItem.getEnable() != null) {
                existingItem.setEnable(updatedItem.getEnable());
            }
            return itemRepository.save(existingItem);
        } else {
            throw new ItemNotFoundException("Item Not Found");
        }
    }

    
    @Override
    public void deleteItem(Long itemId) {
    	Delicacy existingItem = getItemById(itemId);
        itemRepository.delete(existingItem);
    }
}
