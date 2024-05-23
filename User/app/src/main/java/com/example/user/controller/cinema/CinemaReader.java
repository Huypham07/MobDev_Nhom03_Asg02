package com.example.user.controller.cinema;

import com.example.user.model.Cinema;
import com.example.user.model.CinemaHall;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CinemaReader {
    CompletableFuture<List<Cinema>> getAllCinemas(String managerId);
    CompletableFuture<CinemaHall> getCinemaHall(int hallId);
    CompletableFuture<Cinema> getCinema(int cinemaId);
    CompletableFuture<List<Cinema>> getAllCinemas();
    CompletableFuture<List<CinemaHall>> getAllCinemaHalls();
}
