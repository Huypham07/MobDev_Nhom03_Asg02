package com.example.user.controller.show;

import com.example.user.model.Show;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ShowReader {
    CompletableFuture<List<Show>> getAllShowsByCinema(int cinemaId);
    CompletableFuture<List<Show>> getAllShowsByCinemaAndMovie(int cinemaId, int movieId);
    CompletableFuture<List<Show>> getAllShowsByCinemaAndMovie(int cinemaId, int movieId, String date);
}
