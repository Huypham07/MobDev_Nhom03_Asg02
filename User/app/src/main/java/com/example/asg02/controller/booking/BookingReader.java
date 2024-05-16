package com.example.asg02.controller.booking;

import com.example.asg02.model.Booking;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface BookingReader {
    CompletableFuture<List<Booking>> getAllBookings();
    CompletableFuture<List<Booking>> getAllBookings(String UserId);
}
