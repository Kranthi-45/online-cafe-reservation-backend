package com.cafe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cafe.entity.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
	// Custom query methods (if required)
    Optional<User> findByUserName(String username);
}
