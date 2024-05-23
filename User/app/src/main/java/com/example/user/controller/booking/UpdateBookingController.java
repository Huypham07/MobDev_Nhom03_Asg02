package com.example.user.controller.booking;

import com.example.user.model.Booking;
import com.example.user.utils.FirebaseUtils;

public class UpdateBookingController implements BookingUpdater {

    @Override
    public void updateBooking(Booking booking) {
        FirebaseUtils.getDatabaseReference("Bookings").child(String.valueOf(booking.getId())).setValue(booking);
    }
}
