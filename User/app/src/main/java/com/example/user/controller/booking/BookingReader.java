package com.example.user.controller.booking;

import com.example.user.model.Booking;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public interface BookingReader {
    CompletableFuture<List<Booking>> getAllBookings();
    void getBookings(String userId, Consumer<Booking> onBookingAdded);
}
