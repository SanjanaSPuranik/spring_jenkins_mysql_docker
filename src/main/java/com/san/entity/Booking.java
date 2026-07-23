package com.san.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String email;
    private String phone;

    private LocalDate pickupDate;
    private LocalDate returnDate;
    private String pickupLocation;

    @ManyToOne
    private Car car;

    public Booking() {}

    // Getters and Setters
}