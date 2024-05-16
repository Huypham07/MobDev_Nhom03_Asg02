package com.example.asg02.controller.cinema;

import com.example.asg02.model.Cinema;
import com.example.asg02.model.CinemaHall;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetCinemaController implements CinemaReader {
    FirebaseDatabase database;

    public GetCinemaController() {
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public CompletableFuture<List<Cinema>> getAllCinemas(String manager) {
        CompletableFuture<List<Cinema>> future = new CompletableFuture<>();
        database.getReference("Cinemas").get().addOnSuccessListener(dataSnapshot -> {
            List<Cinema> cinemas = new ArrayList<>();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Cinema cinema = snapshot.getValue(Cinema.class);
                if (cinema.getManager().equals(manager)) {
                    cinemas.add(cinema);
                }
            }
            future.complete(cinemas);
        });
        return future;
    }

    @Override
    public CompletableFuture<CinemaHall> getCinemaHall(int hallId) {
        CompletableFuture<CinemaHall> future = new CompletableFuture<>();
        database.getReference("Halls").child(String.valueOf(hallId)).get().addOnSuccessListener(dataSnapshot -> {
            CinemaHall hall = dataSnapshot.getValue(CinemaHall.class);
            future.complete(hall);
        });
        return future;
    }
}
