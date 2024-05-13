package com.example.asg02.model;

public class Hall {
    private int id;
    private String name;
    private String type;
    private int numberOfSeat;
    private int cinemaId;

    public Hall(String name, String type, int numberOfSeat, int cinemaId) {
        this.name = name;
        this.type = type;
        this.numberOfSeat = numberOfSeat;
        this.cinemaId = cinemaId;
        id = hashCode();
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumberOfSeat() {
        return numberOfSeat;
    }

    public void setNumberOfSeat(int numberOfSeat) {
        this.numberOfSeat = numberOfSeat;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
