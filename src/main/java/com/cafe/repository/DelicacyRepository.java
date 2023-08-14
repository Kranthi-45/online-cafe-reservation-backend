package com.cafe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cafe.entity.Delicacy;
import com.cafe.enums.ItemType;

@Repository
public interface DelicacyRepository  extends JpaRepository<Delicacy, Long> {
    
    List<Delicacy> findByType(ItemType itemType);
}
