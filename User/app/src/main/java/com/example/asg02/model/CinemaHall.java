package com.example.asg02.model;

import java.util.List;

public class CinemaHall {
    private String name;
    private int totalSeats;
    private List<Seat> seats;

    public CinemaHall(String name, int totalSeats, List<Seat> seats) {
        this.name = name;
        this.totalSeats = totalSeats;
        this.seats = seats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
