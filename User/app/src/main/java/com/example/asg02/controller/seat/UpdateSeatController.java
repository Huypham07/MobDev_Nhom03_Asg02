package com.example.asg02.controller.seat;

import com.example.asg02.model.Seat;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateSeatController implements SeatUpdater {
    FirebaseDatabase database;

    public UpdateSeatController() {
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public void updateShow(Seat seat) {
        database.getReference("Seats").child(String.valueOf(seat.getId())).setValue(seat);
    }
}
