package com.example.asg02.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Movie {
    private String name;
    private String poster;
    private float rating;
    private int durationMins;
    private String releaseDate;
    private String description;
    private String info;
    private String language;
    private List<Review> reviews = new ArrayList<>();
    private List<Show> shows = new ArrayList<>();

    public Movie(String name, float rating, int durationMins, String releaseDate, String description, String info, String language) {
        this.name = name;
        this.rating = rating;
        this.durationMins = durationMins;
        this.releaseDate = releaseDate;
        this.description = description;
        this.info = info;
        this.language = language;
    }

    public Movie(String name, float rating, int durationMins, String releaseDate, String description, String info, String language, List<Review> reviews, List<Show> shows) {
        this.name = name;
        this.rating = rating;
        this.durationMins = durationMins;
        this.releaseDate = releaseDate;
        this.description = description;
        this.info = info;
        this.language = language;
        this.reviews = reviews;
        this.shows = shows;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getDurationMins() {
        return durationMins;
    }

    public void setDurationMins(int durationMins) {
        this.durationMins = durationMins;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }
}
