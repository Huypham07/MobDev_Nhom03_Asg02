package com.example.asg02.controller.booking;

import com.example.asg02.model.Booking;
import com.google.firebase.database.FirebaseDatabase;

public class CreateBookingController implements BookingCreator {
    FirebaseDatabase database;

    public CreateBookingController () {
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public void createBooking(Booking booking) {
        database.getReference("Bookings").child(String.valueOf(booking.getId())).setValue(booking);
    }
}
