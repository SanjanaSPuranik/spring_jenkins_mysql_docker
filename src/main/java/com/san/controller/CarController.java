package com.san.controller;

import com.san.entity.Car;
import com.san.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    @GetMapping
    public List<Car> getCars() {
        return service.getAllCars();
    }

    @PostMapping
    public Car addCar(@RequestBody Car car) {
        return service.save(car);
    }
}