package com.cafe.service;

import java.util.List;
import java.util.Optional;

import com.cafe.entity.TableCafe;

public interface TableCafeService {
	
    void addTable(TableCafe table);

    List<TableCafe> getTablesByType(String tableType);

    List<TableCafe> getTablesByStatus(String tableStatus);

    List<TableCafe> getAllTables();

    TableCafe reserveTable(Long tableId);

    Optional<TableCafe> getTableById(long id);
}
