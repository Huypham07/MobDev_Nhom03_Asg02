package com.example.asg02.controller.movie;

import android.util.Log;

import com.example.asg02.model.Movie;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetMovieController implements MovieReader {
    private FirebaseDatabase database;

    public GetMovieController() {
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public CompletableFuture<List<Movie>> getAllMovies() {
        CompletableFuture<List<Movie>> future = new CompletableFuture<>();
        database.getReference("Movies").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Movie> movies = new ArrayList<>();
                for (DataSnapshot data : task.getResult().getChildren()) {
                    Movie movie = data.getValue(Movie.class);
                    movies.add(movie);
                }
                future.complete(movies);
            }
        });
        return future;
    }

}
