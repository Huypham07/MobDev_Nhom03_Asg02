package com.example.asg02.model;

public class ShowSeat extends Seat {
    private String seatStatus;
    private double price;
    private Show show;

    public ShowSeat(String seatRow, String seatNumber, String seatType, String seatStatus, double price, Show show) {
        this.seatStatus = seatStatus;
        this.price = price;
        this.show = show;
    }

    public String getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(String seatStatus) {
        this.seatStatus = seatStatus;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }
}
