package com.example.asg02.controller.booking;

import com.example.asg02.model.Booking;
import com.example.asg02.utils.DateTimeUtils;
import com.example.asg02.utils.FirebaseUtils;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetBookingController implements BookingReader {

    public GetBookingController() {}

    @Override
    public CompletableFuture<List<Booking>> getAllBookings() {
        CompletableFuture<List<Booking>> future = new CompletableFuture<>();
        FirebaseUtils.getDatabaseReference("Bookings").get().addOnSuccessListener(dataSnapshot -> {
            List<Booking> bookings = new ArrayList<>();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Booking booking = snapshot.getValue(Booking.class);
                if (booking.getStatus() == Booking.STATUS_AVAILABLE) {
                    String dateTime = booking.getShow().getDate() + " " + booking.getShow().getStartTime();
                    if (!DateTimeUtils.isAfterNow(dateTime)) {
                        booking.setStatus(Booking.STATUS_EXPIRED);
                        new UpdateBookingController().updateBooking(booking);
                    }
                }
                bookings.add(booking);
            }
            future.complete(bookings);
        });
        return future;
    }

    @Override
    public CompletableFuture<List<Booking>> getAllBookings(String userId) {
        CompletableFuture<List<Booking>> future = new CompletableFuture<>();
        FirebaseUtils.getDatabaseReference("Bookings").get().addOnSuccessListener(dataSnapshot -> {
            List<Booking> bookings = new ArrayList<>();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Booking booking = snapshot.getValue(Booking.class);
                if (booking.getUserID().equals(userId)) {
                    if (booking.getStatus() == Booking.STATUS_AVAILABLE) {
                        String dateTime = booking.getShow().getDate() + " " + booking.getShow().getStartTime();
                        if (!DateTimeUtils.isAfterNow(dateTime)) {
                            booking.setStatus(Booking.STATUS_EXPIRED);
                            new UpdateBookingController().updateBooking(booking);
                        }
                    }
                    bookings.add(booking);
                }
            }
            future.complete(bookings);
        });
        return future;
    }
}
