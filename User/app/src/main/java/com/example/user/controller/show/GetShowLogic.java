package com.example.user.controller.show;

import com.example.user.model.Show;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetShowLogic {
    private final ShowReader showReader;

    public GetShowLogic(ShowReader showReader) {
        this.showReader = showReader;
    }

    public CompletableFuture<List<Show>> getAllShowsByCinema(int cinemaId) {
        return showReader.getAllShowsByCinema(cinemaId);
    }

    public CompletableFuture<List<Show>> getAllShowsByCinemaAndMovie(int cinemaId, int movieId) {
        return showReader.getAllShowsByCinemaAndMovie(cinemaId, movieId);
    }

    public CompletableFuture<List<Show>> getAllShowsByCinemaAndMovie(int cinemaId, int movieId, String date) {
        return showReader.getAllShowsByCinemaAndMovie(cinemaId, movieId, date);
    }
}
