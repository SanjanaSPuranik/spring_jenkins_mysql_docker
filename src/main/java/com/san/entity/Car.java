package com.san.entity;

import jakarta.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String carName;
    private String brand;
    private String model;
    private String fuelType;
    private double pricePerDay;
    private boolean available;

    public Car() {}

    // Getters and Setters
}