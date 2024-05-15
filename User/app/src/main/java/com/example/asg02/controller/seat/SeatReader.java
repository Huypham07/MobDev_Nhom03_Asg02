package com.example.asg02.controller.seat;

import com.example.asg02.model.Seat;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface SeatReader {
    CompletableFuture<List<Seat>> getSeatsByHallAndShow(int hallId, int showId);

}
