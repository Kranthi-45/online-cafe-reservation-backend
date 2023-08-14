package com.cafe;

import com.cafe.entity.Delicacy;
import com.cafe.exception.ItemCreationException;
import com.cafe.exception.ItemNotFoundException;
import com.cafe.exception.NoItemsFoundException;
import com.cafe.repository.DelicacyRepository;
import com.cafe.service.DelicacyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DelicacyServiceTests {

    @Mock
    private DelicacyRepository itemRepository;

    @InjectMocks
    private DelicacyServiceImpl itemService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllItems() {
        Delicacy delicacy = new Delicacy();
        when(itemRepository.findAll()).thenReturn(Arrays.asList(delicacy));

        List<Delicacy> results = itemService.getAllItems();

        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
    }

    @Test
    void testGetAllItems_NoItems() {
        when(itemRepository.findAll()).thenReturn(Arrays.asList());

        assertThrows(NoItemsFoundException.class, () -> itemService.getAllItems());
    }

    @Test
    void testGetItemById() {
        Delicacy delicacy = new Delicacy();
        when(itemRepository.findById(1L)).thenReturn(Optional.of(delicacy));

        Delicacy result = itemService.getItemById(1L);

        assertNotNull(result);
    }

    @Test
    void testGetItemById_NotFound() {
        when(itemRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class, () -> itemService.getItemById(1L));
    }

    @Test
    void testCreateItem() {
        Delicacy delicacy = new Delicacy();
        when(itemRepository.save(any(Delicacy.class))).thenReturn(delicacy);

        Delicacy result = itemService.createItem(delicacy);

        assertNotNull(result);
    }

    @Test
    void testCreateItem_Exception() {
        Delicacy delicacy = new Delicacy();
        when(itemRepository.save(any(Delicacy.class))).thenThrow(RuntimeException.class);

        assertThrows(ItemCreationException.class, () -> itemService.createItem(delicacy));
    }

    @Test
    void testUpdateItem() {
        Delicacy existingItem = new Delicacy();
        Delicacy updatedItem = new Delicacy();
        updatedItem.setItemName("Updated Name");
        when(itemRepository.findById(1L)).thenReturn(Optional.of(existingItem));
        when(itemRepository.save(any(Delicacy.class))).thenReturn(updatedItem);

        Delicacy result = itemService.updateItem(1L, updatedItem);

        assertEquals("Updated Name", result.getItemName());
    }

    @Test
    void testUpdateItem_NotFound() {
        Delicacy updatedItem = new Delicacy();
        when(itemRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class, () -> itemService.updateItem(1L, updatedItem));
    }

    @Test
    void testDeleteItem() {
        Delicacy existingItem = new Delicacy();
        when(itemRepository.findById(1L)).thenReturn(Optional.of(existingItem));

        itemService.deleteItem(1L);

        verify(itemRepository, times(1)).delete(existingItem);
    }

    @Test
    void testDeleteItem_NotFound() {
        when(itemRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class, () -> itemService.deleteItem(1L));
    }
}
