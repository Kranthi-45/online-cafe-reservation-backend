package com.cafe.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private Date date;
    private String time;
    private int numberOfPeople;
    private String customMessage;
    private Date createdDate;
    private String status; // "Booking pending" or "Booking confirmed"

    @ManyToOne
    @JoinColumn(name = "user_id") // Assuming user_id is the foreign key column
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "table_id") // Assuming table_id is the foreign key column
    private TableCafe table;

}
