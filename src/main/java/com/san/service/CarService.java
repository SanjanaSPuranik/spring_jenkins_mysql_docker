package com.san.service;

import com.san.entity.Car;
import com.san.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository repository;

    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    public List<Car> getAllCars() {
        return repository.findAll();
    }

    public Car save(Car car) {
        return repository.save(car);
    }
}