package com.example.asg02.model;

public class Show {
    private int id;
    private int cinemaId;
    private int hallId;
    private int movieId;
    private String startTime;
    private String endTime;
    private String date;
    public Show() {

    }

    public Show(int cinemaId, int hallId, int movieId, String startTime, String endTime, String date) {
        this.cinemaId = cinemaId;
        this.hallId = hallId;
        this.movieId = movieId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        id = hashCode();
    }

    public Show(int id, int cinemaId, int hallId, int movieId, String startTime, String endTime, String date) {
        this.id = id;
        this.cinemaId = cinemaId;
        this.hallId = hallId;
        this.movieId = movieId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
