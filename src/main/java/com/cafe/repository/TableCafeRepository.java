package com.cafe.repository;

import com.cafe.entity.TableCafe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableCafeRepository extends JpaRepository<TableCafe, Long> {
    List<TableCafe> findByTableType(String tableType);
    
    List<TableCafe> findByTableStatus(String tableStatus);
}
