package com.cafe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe.entity.TableCafe;
import com.cafe.repository.TableCafeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TableCafeServiceImpl implements TableCafeService{
    private final TableCafeRepository tableRepository;

    @Autowired
    public TableCafeServiceImpl(TableCafeRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public void addTable(TableCafe table) {
    	tableRepository.save(table);
    }

    public List<TableCafe> getTablesByType(String tableType) {
        return tableRepository.findByTableType(tableType);
    }

    public List<TableCafe> getTablesByStatus(String tableStatus) {
        return tableRepository.findByTableStatus(tableStatus);
    }

    public List<TableCafe> getAllTables() {
        return tableRepository.findAll();
    }

    public TableCafe reserveTable(Long tableId) {
        Optional<TableCafe> optionalTable = tableRepository.findById(tableId);
        if (optionalTable.isPresent()) {
        	TableCafe table = optionalTable.get();
            if (!"Reserved".equals(table.getTableStatus())) {
                table.setTableStatus("Reserved");
                tableRepository.save(table);
                return table;
            } else {
                throw new RuntimeException("Table is already reserved.");
            }
        }
		return null;
    }

    // Method to search a table by its ID
    public Optional<TableCafe> getTableById(long id) {
        return tableRepository.findById(id);
    }
}
