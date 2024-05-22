package com.example.user.controller.movie;

import com.example.user.model.Movie;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class GetMovieLogic {
    private final MovieReader movieReader;

    public GetMovieLogic(MovieReader movieReader) {
        this.movieReader = movieReader;
    }

    public void getAllMovies(Consumer<Movie> onMovieAdded) {
        movieReader.getAllMovies(onMovieAdded);
    }

    public CompletableFuture<Movie> getMovie(int movieId) {
        return movieReader.getMovie(movieId);
    }
}
