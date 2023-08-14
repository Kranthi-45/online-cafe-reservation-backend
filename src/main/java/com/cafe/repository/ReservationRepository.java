package com.cafe.repository;

import com.cafe.entity.Reservation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserUserId(Long userId); // Use the appropriate name for the user's primary key column
}
