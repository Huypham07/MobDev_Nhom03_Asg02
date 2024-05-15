package com.example.asg02.model;

public class Seat {

    public static final int NOT_IN_SHOW = -1;
    private int id;
    private String seatRow;
    private int seatNumber;
    private int hallId;
    private int showId;
    private int status;
    private double price;

    public static final int BOOKED = 1;
    public static final int AVAILABLE = 2;

    public Seat() {
    }

    public Seat(String seatRow, int seatNumber, int hallId, int showId, double price) {
        this.seatRow = seatRow;
        this.seatNumber = seatNumber;
        this.hallId = hallId;
        this.showId = showId;
        this.price = price;
        status = AVAILABLE;
        id = hashCode();
    }

    public Seat(String seatRow, int seatNumber, int hallId, double price) {
        this.seatRow = seatRow;
        this.seatNumber = seatNumber;
        this.hallId = hallId;
        this.price = price;
        status = AVAILABLE;
        showId = NOT_IN_SHOW;
        id = hashCode();
    }

    public String getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(String seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


/*    //in interface


    //implement



    double lowPrice = Double.parseDouble(editLowPrice.getText().toString());
    double highPrice = Double.parseDouble(editHighPrice.getText().toString());
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            if (i >= row / 2) {
                Seat seat = new Seat(i, j, this.id, highPrice);
            } else {
                Seat seat = new Seat(i, j, this.id, lowPrice);
            }

            createSeatController.createNewSeat(seat);
        }
    }*/


}
