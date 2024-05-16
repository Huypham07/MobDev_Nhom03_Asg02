package com.example.asg02.controller.booking;

import com.example.asg02.model.Booking;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetBookingController implements BookingReader {
    FirebaseDatabase database;

    public GetBookingController() {
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public CompletableFuture<List<Booking>> getAllBookings() {
        CompletableFuture<List<Booking>> future = new CompletableFuture<>();
        database.getReference("Bookings").get().addOnSuccessListener(dataSnapshot -> {
            List<Booking> bookings = new ArrayList<>();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Booking booking = snapshot.getValue(Booking.class);
                    bookings.add(booking);
            }
            future.complete(bookings);
        });
        return future;
    }

    @Override
    public CompletableFuture<List<Booking>> getAllBookings(String userId) {
        CompletableFuture<List<Booking>> future = new CompletableFuture<>();
        database.getReference("Bookings").get().addOnSuccessListener(dataSnapshot -> {
            List<Booking> bookings = new ArrayList<>();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Booking booking = snapshot.getValue(Booking.class);
                if (booking.getUserID().equals(userId)) {
                    bookings.add(booking);
                }
            }
            future.complete(bookings);
        });
        return future;
    }
}
