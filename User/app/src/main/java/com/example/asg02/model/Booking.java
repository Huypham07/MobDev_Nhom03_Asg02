package com.example.asg02.model;

import java.util.ArrayList;
import java.util.List;

public class Booking {
    private int bookingNumber;
    private String userEmail;
    private Show show;
    private List<ShowSeat> seats = new ArrayList<>();
    private List<CarryItem> carryItems = new ArrayList<>();
    private String bookingStatus;
    private Payment payment;

    public Booking(int bookingNumber, String userEmail, Show show) {
        this.bookingNumber = bookingNumber;
        this.userEmail = userEmail;
        this.show = show;
    }

    public int getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
