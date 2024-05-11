package com.example.asg02.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Movie implements Serializable {
    private String name;
    private String poster;
    private String genre;
    private int durationMins;
    private String releaseDate;
    private String description;
    private String director;
    private String actors;
    private String language;
    private float rating;
    private List<Review> reviews = new ArrayList<>();
    private List<Show> shows = new ArrayList<>();

    public Movie() {
    }

    public Movie(String name, String poster, String genre, int durationMins
            , String releaseDate, String description, String director, String actors, String language) {
        this.name = name;
        this.poster = poster;
        this.genre = genre;
        this.durationMins = durationMins;
        this.releaseDate = releaseDate;
        this.description = description;
        this.director = director;
        this.actors = actors;
        this.language = language;
    }

    public Movie(String name, String poster, String genre, int durationMins
            , String description, String director, String actors, String language) {
        this.name = name;
        this.poster = poster;
        this.genre = genre;
        this.durationMins = durationMins;
        this.description = description;
        this.director = director;
        this.actors = actors;
        this.language = language;
    }

    public Movie(String name, String poster, String genre, int durationMins
            , String releaseDate, String description, String director
            , String actors, String language, float rating) {
        this.name = name;
        this.poster = poster;
        this.genre = genre;
        this.durationMins = durationMins;
        this.releaseDate = releaseDate;
        this.description = description;
        this.director = director;
        this.actors = actors;
        this.language = language;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
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
