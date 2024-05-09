package com.example.asg02;

public class Movie {
    private String title;
    private String year;
    private String reated;
    private String released;
    private String runtime;
    private String genre;

    public Movie(String title, String year, String reated, String released, String runtime, String genre, String director, String writer, String actor, String plot, String language, String awards, String metascore, String imdbRating, String imdbVotes, String imdbID) {
        this.title = title;
        this.year = year;
        this.reated = reated;
        this.released = released;
        this.runtime = runtime;
        this.genre = genre;
        this.director = director;
        this.writer = writer;
        this.actor = actor;
        this.plot = plot;
        this.language = language;
        this.awards = awards;
        this.metascore = metascore;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
        this.imdbID = imdbID;
    }

    private String director;
    private String writer;
    private String actor;
    private String plot;
    private String language;
    private String awards;
    private String metascore;
    private String imdbRating;
    private String imdbVotes;
    private String imdbID;



}
