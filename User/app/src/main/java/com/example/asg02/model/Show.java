package com.example.asg02.model;

import java.util.Date;
import java.util.Timer;

public class Show {
    private Date createOn;
    private String startTime;
    private String endTime;
    private CinemaHall cinemaHall;

    public Show(Date createOn, String startTime, String endTime, CinemaHall cinemaHall) {
        this.createOn = createOn;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cinemaHall = cinemaHall;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }
}
