package com.example.user.controller.movie;

import com.example.user.model.Movie;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public interface MovieReader {
    void getAllMovies(Consumer<Movie> onBookingAdded);
    CompletableFuture<Movie> getMovie(int movieId);
}
