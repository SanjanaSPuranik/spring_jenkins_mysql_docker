package com.san.service;

import com.san.entity.Booking;
import com.san.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository repository;

    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    public Booking save(Booking booking) {
        return repository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return repository.findAll();
    }
}