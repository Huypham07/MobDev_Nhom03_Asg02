package com.example.asg02.model;

public class Hall {
    private int id;
    private String name;
    private int seatsPerColumn;
    private int seatsPerRow;
    private int cinemaId;

    public Hall(String name, int seatsPerRow, int seatsPerColumn, int cinemaId) {
        this.name = name;
        this.seatsPerColumn = seatsPerColumn;
        this.seatsPerRow = seatsPerRow;
        this.cinemaId = cinemaId;
        id = hashCode();
    }

    public Hall(int id, String name, int seatsPerRow, int seatsPerColumn, int cinemaId) {
        this.id = id;
        this.name = name;
        this.seatsPerColumn = seatsPerColumn;
        this.seatsPerRow = seatsPerRow;
        this.cinemaId = cinemaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeatsPerColumn() {
        return seatsPerColumn;
    }

    public void setSeatPerColumn(int seatPerColumn) {
        this.seatsPerColumn = seatPerColumn;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public void setSeatsPerRow(int seatsPerRow) {
        this.seatsPerRow = seatsPerRow;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
