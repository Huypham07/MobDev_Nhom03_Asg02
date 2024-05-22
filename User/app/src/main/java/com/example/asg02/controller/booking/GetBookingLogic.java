package com.example.asg02.controller.booking;

import com.example.asg02.model.Booking;
import com.example.asg02.utils.DateTimeUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class GetBookingLogic {
    private final BookingReader bookingReader;
    private final UpdateBookingController updateBookingController;

    public GetBookingLogic(BookingReader bookingReader, UpdateBookingController updateBookingController) {
        this.bookingReader = bookingReader;
        this.updateBookingController = updateBookingController;
    }

    public CompletableFuture<List<Booking>> getAllBookings() {
        CompletableFuture<List<Booking>> future = new CompletableFuture<>();
        bookingReader.getAllBookings().thenAccept(bookings -> {
            for (Booking booking : bookings) {
                if (booking.getStatus() == Booking.STATUS_AVAILABLE) {
                    String dateTime = booking.getShow().getDate() + " " + booking.getShow().getEndTime();
                    if (!DateTimeUtils.isAfterNow(dateTime)) {
                        booking.setStatus(Booking.STATUS_EXPIRED);
                        updateBookingController.updateBooking(booking);
                    }
                }
            }
            future.complete(bookings);
        });
        return future;
    }

    public void getBookings(String userId, Consumer<Booking> onBookingAdded) {
        bookingReader.getBookings(userId, booking -> {
            if (booking.getStatus() == Booking.STATUS_AVAILABLE) {
                String dateTime = booking.getShow().getDate() + " " + booking.getShow().getEndTime();
                if (!DateTimeUtils.isAfterNow(dateTime)) {
                    booking.setStatus(Booking.STATUS_EXPIRED);
                    updateBookingController.updateBooking(booking);
                }
            }
            onBookingAdded.accept(booking);
        });
    }
}