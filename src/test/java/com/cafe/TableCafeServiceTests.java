package com.cafe;

import com.cafe.entity.TableCafe;
import com.cafe.repository.TableCafeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import com.cafe.service.TableCafeServiceImpl;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TableCafeServiceTests {

    @Mock
    private TableCafeRepository tableRepository;

    @InjectMocks
    private TableCafeServiceImpl tableService;

    @BeforeEach
    public void setup() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddTable() {
        TableCafe table = new TableCafe();
        tableService.addTable(table);
        verify(tableRepository, times(1)).save(table);
    }

    @Test
    public void testGetTablesByType() {
        String type = "VIP";

        tableService.getTablesByType(type);
        verify(tableRepository, times(1)).findByTableType(type);
    }

    @Test
    public void testGetTablesByStatus() {
        String status = "Available";
        tableService.getTablesByStatus(status);
        verify(tableRepository, times(1)).findByTableStatus(status);
    }

    @Test
    public void testGetAllTables() {
        tableService.getAllTables();

        verify(tableRepository, times(1)).findAll();
    }

    @Test
    public void testReserveTableAlreadyReserved() {
        Long tableId = 1L;
        TableCafe table = new TableCafe(tableId, "Table1", "VIP", "Reserved");
        when(tableRepository.findById(tableId)).thenReturn(Optional.of(table));

        assertThrows(RuntimeException.class, () -> tableService.reserveTable(tableId));
    }

    @Test
    public void testReserveTableSuccess() {
        Long tableId = 1L;
        TableCafe table = new TableCafe(tableId, "Table1", "VIP", "Available");
        when(tableRepository.findById(tableId)).thenReturn(Optional.of(table));

        TableCafe reservedTable = tableService.reserveTable(tableId);
        assertEquals("Reserved", reservedTable.getTableStatus());
    }

    @Test
    public void testGetTableById() {
        Long tableId = 1L;
        TableCafe table = new TableCafe();
        
        when(tableRepository.findById(tableId)).thenReturn(Optional.of(table));

        Optional<TableCafe> fetchedTable = tableService.getTableById(tableId);
        assertTrue(fetchedTable.isPresent());
    }

}
