package com.example.asg02.model;

public class Hall {
    private int id;
    private String name;
    private int seatPerColumn;
    private int seatsPerRow;
    private int cinemaId;

    public Hall(String name, int seatPerRow, int seatsPerColumn, int cinemaId) {
        this.name = name;
        this.seatPerColumn = seatPerColumn;
        this.seatsPerRow = seatsPerRow;
        this.cinemaId = cinemaId;
        id = hashCode();
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

    public int getSeatPerColumn() {
        return seatPerColumn;
    }

    public void setSeatPerColumn(int seatPerColumn) {
        this.seatPerColumn = seatPerColumn;
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
