package com.cafe.service;

import com.cafe.entity.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> getAllReservations();

    Reservation getReservationById(Long id);

    Reservation createReservation(Reservation reservation);

    Reservation updateReservation(Long id, Reservation reservation);

    void deleteReservation(Long id);

	List<Reservation> getReservationsByUserId(Long userId);
}
