package com.example.asg02.controller.seat;

import com.example.asg02.model.Seat;
import com.google.firebase.database.FirebaseDatabase;

public class CreateSeatController implements SeatCreator {
    FirebaseDatabase database;

    public CreateSeatController() {
        database = FirebaseDatabase.getInstance();
    }

    public void createSeat(Seat seat) {
        database.getReference("Seats").child(String.valueOf(seat.getId())).setValue(seat);
    }
}
