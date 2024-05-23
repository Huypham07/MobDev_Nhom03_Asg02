package com.example.user.controller.booking;

import com.example.user.model.Booking;
import com.example.user.utils.FirebaseUtils;

public class CreateBookingController implements BookingCreator {

    public CreateBookingController () {
    }

    @Override
    public void createBooking(Booking booking) {
        FirebaseUtils.getDatabaseReference("Bookings").child(String.valueOf(booking.getId())).setValue(booking);
    }
}
