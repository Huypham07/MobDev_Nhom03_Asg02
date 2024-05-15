package com.example.asg02.controller.movie;

import com.example.asg02.model.Movie;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MovieReader {
    CompletableFuture<List<Movie>> getAllMovies();
}
