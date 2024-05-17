package com.example.asg02.controller.movie;

import com.example.asg02.model.Movie;
import com.example.asg02.utils.FirebaseUtils;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetMovieController implements MovieReader {

    public GetMovieController() {
    }

    @Override
    public CompletableFuture<List<Movie>> getAllMovies() {
        CompletableFuture<List<Movie>> future = new CompletableFuture<>();
        FirebaseUtils.getDatabaseReference("Movies").get().addOnCompleteListener(task -> {
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
