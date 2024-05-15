package com.example.asg02.model;

import java.util.ArrayList;
import java.util.List;

public class Booking {
    private int id;
    private String userID;
    private Show show;
    private List<ShowSeat> seats = new ArrayList<>();
    private List<CarryItem> carryItems = new ArrayList<>();
    private int bookingStatus;
    private Payment payment;

    public static final int PENDING = 0;
    public static final int EXPIRED = 2;
    public static final int PAID = 1;

    public Booking() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public List<ShowSeat> getSeats() {
        return seats;
    }

    public void setSeats(List<ShowSeat> seats) {
        this.seats = seats;
    }

    public List<CarryItem> getCarryItems() {
        return carryItems;
    }

    public void setCarryItems(List<CarryItem> carryItems) {
        this.carryItems = carryItems;
    }

    public int getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(int bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
