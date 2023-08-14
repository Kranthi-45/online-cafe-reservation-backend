package com.cafe.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.cafe.entity.Delicacy;
import com.cafe.enums.ItemType;

@Service
public interface DelicacyService {
    List<Delicacy> getAllItems();
    Delicacy getItemById(Long itemId);
    Delicacy createItem(Delicacy item);
    Delicacy updateItem(Long itemId, Delicacy item);
    void deleteItem(Long itemId);
    List<Delicacy> getItemsByType(ItemType itemType);
}
