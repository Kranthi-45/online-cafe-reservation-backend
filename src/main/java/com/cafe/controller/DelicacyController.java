package com.cafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;

import com.cafe.entity.Delicacy;
import com.cafe.exception.InvalidItemException;
import com.cafe.exception.ItemNotFoundException;
import com.cafe.service.DelicacyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = {"http://localhost:4200","*"}, allowedHeaders = "*")
public class DelicacyController {
    private final DelicacyService itemService;

    @Autowired
    public DelicacyController(DelicacyService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllItems() {
        List<Delicacy> items = itemService.getAllItems();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "All Items fetched successfully.");
        response.put("items", items);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Map<String, Object>> getItemById(@PathVariable Long itemId) {
    	Delicacy item = itemService.getItemById(itemId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Item fetched successfully with ID: " + itemId);
        response.put("item", item);
        return ResponseEntity.status(HttpStatus.OK).body(response);    
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createItem(@RequestBody Delicacy item) {
        if (item.getItemId() != null) {
            throw new InvalidItemException("Item ID must not be provided while creating a new item.");
        }
        Delicacy createdItem = itemService.createItem(item);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Item added successfully.");
        response.put("item", createdItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

	@PutMapping("/{itemId}")
	public ResponseEntity<Map<String, Object>> updateItem(@PathVariable Long itemId, @RequestBody Delicacy item) {
		if (!item.getItemId().equals(itemId)) 
			throw new InvalidItemException("Item ID mismatch.");
		Delicacy updatedItem = itemService.updateItem(itemId, item);
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Item updated successfully.");
		response.put("item", updatedItem);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/{itemId}")
	public ResponseEntity<Map<String, Object>> deleteItem(@PathVariable Long itemId) {
		itemService.deleteItem(itemId);
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Item deleted successfully.");
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
	}
}
