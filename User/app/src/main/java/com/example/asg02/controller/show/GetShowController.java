package com.example.asg02.controller.show;

import android.util.Log;

import com.example.asg02.model.Cinema;
import com.example.asg02.model.Show;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetShowController implements ShowReader {
    FirebaseDatabase database;

    public GetShowController() {
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public CompletableFuture<List<Show>> getAllShowsByCinema(int cinemaId) {
        CompletableFuture<List<Show>> future = new CompletableFuture<>();
        database.getReference("Shows").get().addOnSuccessListener(dataSnapshot -> {
            List<Show> shows = new ArrayList<>();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Show show = snapshot.getValue(Show.class);
                if (show.getCinemaId() == cinemaId) {
                    shows.add(show);
                }
            }
            future.complete(shows);
        });
        return future;
    }

    @Override
    public CompletableFuture<List<Show>> getAllShowsByCinemaAndMovie(int cinemaId, int movieId) {
        CompletableFuture<List<Show>> future = new CompletableFuture<>();
        database.getReference("Shows").get().addOnSuccessListener(dataSnapshot -> {
            List<Show> shows = new ArrayList<>();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Show show = snapshot.getValue(Show.class);
                if (show.getCinemaId() == cinemaId && show.getMovieId() == movieId) {
                    shows.add(show);
                }
            }
            future.complete(shows);
        });
        return future;
    }

    @Override
    public CompletableFuture<List<Show>> getAllShowsByCinemaAndMovie(int cinemaId, int movieId, String date) {
        CompletableFuture<List<Show>> future = new CompletableFuture<>();
        database.getReference("Shows").get().addOnSuccessListener(dataSnapshot -> {
            List<Show> shows = new ArrayList<>();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Show show = snapshot.getValue(Show.class);
                if (show.getCinemaId() == cinemaId && show.getMovieId() == movieId && show.getDate().equals(date)) {
                    shows.add(show);
                }
            }
            future.complete(shows);
        });
        return future;
    }
}
