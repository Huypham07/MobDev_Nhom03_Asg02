package com.example.asg02.controller;

import androidx.annotation.NonNull;

import com.example.asg02.model.Movie;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetMovieController implements MovieReader {
    private FirebaseDatabase database;
    private LocalDate currentDate = LocalDate.now();

    public GetMovieController() {
        database = FirebaseDatabase.getInstance();
    }
    public List<Movie> getAllCurrentMovies() {
        List<Movie> CurrMovies = new ArrayList<>();
        getAllMovies().thenAccept(movies -> {
            for (Movie movie : movies) {
                if (isCurrentMovie(movie.getReleaseDate())) {
                    CurrMovies.add(movie);
                }
            }
        });
        return CurrMovies;
    }

    public List<Movie> getAllUpcomingMovies() {
        List<Movie> upcomingMovies = new ArrayList<>();
        getAllMovies().thenAccept(movies -> {
            for (Movie movie : movies) {
                if (!isCurrentMovie(movie.getReleaseDate())) {
                    upcomingMovies.add(movie);
                }
            }
        });
        return upcomingMovies;
    }

    @Override
    public CompletableFuture<List<Movie>> getAllMovies() {
        return null;
    }

    private boolean isCurrentMovie(String releaseDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(releaseDate, formatter);

        if (date.isBefore(currentDate)) {
            return true;
        }
        return false;
    }
}
