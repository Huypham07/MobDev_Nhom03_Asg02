package com.example.asg02.controller.cinema;

import com.example.asg02.model.Cinema;
import com.example.asg02.model.CinemaHall;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetCinemaLogic {
    private final CinemaReader cinemaReader;

    public GetCinemaLogic(CinemaReader cinemaReader) {
        this.cinemaReader = cinemaReader;
    }

    public CompletableFuture<List<Cinema>> getAllCinemas() {
        return cinemaReader.getAllCinemas();
    }

    public CompletableFuture<List<Cinema>> getAllCinemas(String manager) {
        return cinemaReader.getAllCinemas(manager);
    }

    public CompletableFuture<Cinema> getCinema(int cinemaId) {
        return cinemaReader.getCinema(cinemaId);
    }

    public CompletableFuture<CinemaHall> getCinemaHall(int hallId) {
        return cinemaReader.getCinemaHall(hallId);
    }

    public CompletableFuture<List<CinemaHall>> getAllCinemaHalls() {
        return cinemaReader.getAllCinemaHalls();
    }
}
