package com.example.asg02.controller.seat;

import com.example.asg02.model.Seat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetSeatController implements SeatReader {
    FirebaseDatabase database;

    public GetSeatController() {
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public CompletableFuture<List<Seat>> getSeatsByHallAndShow(int hallId, int showId) {
        CompletableFuture<List<Seat>> future = new CompletableFuture<>();
        database.getReference("Seats").get().addOnSuccessListener(dataSnapshot -> {
            List<Seat> seats = new ArrayList<>();
            for (DataSnapshot data : dataSnapshot.getChildren()) {
                Seat s = data.getValue(Seat.class);
                if (s.getShowId() == showId && s.getHallId() == hallId) {
                    seats.add(s);
                }
            }
            future.complete(seats);
        });
        return future;
    }

    public void sortListofSeat(List<Seat> seats) {
        Comparator<Seat> seatComparator = new Comparator<Seat>() {
            @Override
            public int compare(Seat s1, Seat s2) {
                int rowComparison = s1.getSeatRow().compareTo(s2.getSeatRow());
                if (rowComparison != 0) {
                    return rowComparison;
                } else {
                    return Integer.compare(s1.getSeatNumber(), s2.getSeatNumber());
                }
            }
        };

        // Sort the list
        Collections.sort(seats, seatComparator);
    }
}
