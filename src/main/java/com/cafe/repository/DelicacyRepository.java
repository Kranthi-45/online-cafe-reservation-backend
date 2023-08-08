package com.cafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cafe.entity.Delicacy;

@Repository
public interface DelicacyRepository  extends JpaRepository<Delicacy, Long> {
    
	// Custom query methods (if required)
}
