package com.cafe;

import com.cafe.entity.Reservation;
import com.cafe.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import com.cafe.service.ReservationServiceImpl;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ReservationTests {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @BeforeEach
    public void setup() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllReservations() {
        when(reservationRepository.findAll()).thenReturn(Arrays.asList(new Reservation(), new Reservation()));
        
        assertEquals(2, reservationService.getAllReservations().size());
    }

    @Test
    public void testGetReservationById() {
        Long id = 1L;
        Reservation reservation = new Reservation();

        when(reservationRepository.findById(id)).thenReturn(Optional.of(reservation));

        assertNotNull(reservationService.getReservationById(id));
    }

    @Test
    public void testGetReservationsByUserId() {
        Long userId = 1L;
        when(reservationRepository.findByUserUserId(userId)).thenReturn(Arrays.asList(new Reservation()));

        assertEquals(1, reservationService.getReservationsByUserId(userId).size());
    }

    @Test
    public void testCreateReservation() {
        Reservation reservation = new Reservation();

        when(reservationRepository.save(reservation)).thenReturn(reservation);

        assertEquals(reservation, reservationService.createReservation(reservation));
    }

    @Test
    public void testUpdateReservation() {
        Long id = 1L;
        Reservation reservation = new Reservation();

        when(reservationRepository.existsById(id)).thenReturn(true);
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        assertNotNull(reservationService.updateReservation(id, reservation));
    }

    @Test
    public void testDeleteReservation() {
        Long id = 1L;

        doNothing().when(reservationRepository).deleteById(id);

        reservationService.deleteReservation(id);

        verify(reservationRepository, times(1)).deleteById(id);
    }
}
