package com.cafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cafe.entity.Delicacy;
import com.cafe.entity.TableCafe;
import com.cafe.service.TableCafeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/tables")
@CrossOrigin(origins = {"http://localhost:4200", "*"}, allowedHeaders = "*")
public class TableCafeController {
    private final TableCafeService tableService;

    @Autowired
    public TableCafeController(TableCafeService tableService) {
        this.tableService = tableService;
    }
    
    @PostMapping("/add")
    public ResponseEntity<String> addTable(@RequestBody TableCafe table) {
    	tableService.addTable(table);
        return ResponseEntity.status(HttpStatus.CREATED).body("Table added successfully.");
    }

    @GetMapping("/ac/{tableType}")
    public ResponseEntity<List<TableCafe>> getAcTables(@PathVariable String tableType) {
        List<TableCafe> acTables = tableService.getTablesByType(tableType);
        return ResponseEntity.ok(acTables);
    }

    @GetMapping("/reserved")
    public ResponseEntity<List<TableCafe>> getReservedTables() {
        List<TableCafe> reservedTables = tableService.getTablesByStatus("Reserved");
        return ResponseEntity.ok(reservedTables);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TableCafe>> getAllTables() {
        List<TableCafe> allTables = tableService.getAllTables();
        return ResponseEntity.ok(allTables);
    }

//    @PostMapping("/reserve/{tableId}")
//    public ResponseEntity<String> reserveTable(@PathVariable Long tableId) {
//        try {
//            tableService.reserveTable(tableId);
//            return ResponseEntity.ok("Table reserved successfully.");
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body("Table is already reserved.");
//        }
//    }
    @PostMapping("/reserve/{tableId}")
    public ResponseEntity<Map<String, Object>> reserveTable(@PathVariable Long tableId) {
        try {

    		TableCafe reservedtable = tableService.reserveTable(tableId);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Table reserved successfully.");
    		response.put("table", reservedtable);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Table reservation failed: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TableCafe> getTableById(@PathVariable long id) {
        Optional<TableCafe> table = tableService.getTableById(id);
        return table.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
