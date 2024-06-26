package com.example.admin.model;

import java.io.Serializable;

public class Movie implements Serializable {
    private int id;
    private String name;
    private String poster;
    private String trailerLink;
    private float rating;
    private String releaseDate;
    private int durationMins;
    private String description;
    private String censor;
    private String genre;
    private String director;
    private String actors;
    private String language;

    public Movie() {
    }

    public Movie(String name, String poster, String trailerLink, String releaseDate, int durationMins,
                 String description, String censor, String genre, String director, String actors, String language) {
        this.name = name;
        this.poster = poster;
        this.trailerLink = trailerLink;
        this.releaseDate = releaseDate;
        this.durationMins = durationMins;
        this.description = description;
        this.censor = censor;
        this.genre = genre;
        this.director = director;
        this.actors = actors;
        this.language = language;
        id = hashCode();
    }

    public String getCensor() {
        return censor;
    }

    public void setCensor(String censor) {
        this.censor = censor;
    }

    public int getId() {
        return id;
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

    public String getTrailerLink() {
        return trailerLink;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
