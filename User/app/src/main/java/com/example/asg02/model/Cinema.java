package com.example.asg02.model;

import java.util.List;

public class Cinema {
    private String name;
    private int totalHall;
    private Address location;
    private List<CinemaHall> cinemaHalls;

    public Cinema(String name, int totalHall, Address location) {
        this.name = name;
        this.totalHall = totalHall;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalHall() {
        return totalHall;
    }

    public void setTotalHall(int totalHall) {
        this.totalHall = totalHall;
    }

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }

    public List<CinemaHall> getCinemaHalls() {
        return cinemaHalls;
    }

    public void setCinemaHalls(List<CinemaHall> cinemaHalls) {
        this.cinemaHalls = cinemaHalls;
    }
}
