package com.san.controller;

import com.san.entity.Booking;
import com.san.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping
    public List<Booking> getBookings() {
        return service.getAllBookings();
    }

    @PostMapping
    public Booking addBooking(@RequestBody Booking booking) {
        return service.save(booking);
    }
}