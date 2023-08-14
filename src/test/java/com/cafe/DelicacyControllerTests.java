package com.cafe;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.cafe.exception.InvalidItemException;

import com.cafe.entity.Delicacy;
import com.cafe.service.DelicacyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.cafe.controller.DelicacyController;
import java.util.Arrays;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(DelicacyController.class)
public class DelicacyControllerTests {

	
	
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DelicacyService itemService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAllItems() throws Exception {
        Delicacy delicacy = new Delicacy();
        when(itemService.getAllItems()).thenReturn(Arrays.asList(delicacy));

        mockMvc.perform(get("/api/items"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.message").value("All Items fetched successfully."));
    }

    @Test
    void testGetItemById() throws Exception {
        Delicacy delicacy = new Delicacy(); 
        when(itemService.getItemById(anyLong())).thenReturn(delicacy);

        mockMvc.perform(get("/api/items/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.message").value("Item fetched successfully with ID: 1"));
    }

    
    
    
    @Test
    void testCreateItem() throws Exception {
        Delicacy itemRequest = new Delicacy(); 
        Delicacy createdItem = new Delicacy(); 
        when(itemService.createItem(any(Delicacy.class))).thenReturn(createdItem);

        mockMvc.perform(post("/api/items")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(itemRequest)))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.message").value("Item added successfully."));
    }
        @Test
        void testUpdateItem() throws Exception {
            Delicacy updateRequest = new Delicacy();
            updateRequest.setItemId(1L); 
            Delicacy updatedItem = new Delicacy(); 
            when(itemService.updateItem(eq(1L), any(Delicacy.class))).thenReturn(updatedItem);

            mockMvc.perform(put("/api/items/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                   .andExpect(status().isOk())
                   .andExpect(jsonPath("$.message").value("Item updated successfully."));
        }

        @Test
        void testUpdateItemMismatch() throws Exception {
            Delicacy updateRequest = new Delicacy();
            updateRequest.setItemId(2L);
            
            mockMvc.perform(put("/api/items/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                   .andExpect(status().isBadRequest())
                   .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidItemException))
                   .andExpect(result -> assertEquals("Item ID mismatch.", result.getResolvedException().getMessage()));
        }

        @Test
        void testDeleteItem() throws Exception {
            doNothing().when(itemService).deleteItem(1L);

            mockMvc.perform(delete("/api/items/1")
                        .contentType(MediaType.APPLICATION_JSON))
                   .andExpect(status().isOk())
                   .andExpect(jsonPath("$.message").value("Item deleted successfully."));
        }
    }

