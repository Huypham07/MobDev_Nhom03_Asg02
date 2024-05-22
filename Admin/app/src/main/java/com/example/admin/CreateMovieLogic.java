package com.example.admin;

import com.example.admin.controller.CreateMovieController;
import com.example.admin.model.Movie;

public class CreateMovieLogic {
    private CreateMovieController createMovieController;

    public CreateMovieLogic(CreateMovieController createMovieController) {
        this.createMovieController = createMovieController;
    }

    public void addMovie(String movieName, String poster, String trailerLink, String releaseDate,
                         int durationMins, String description, String censor, String genre,
                         String director, String actors, String language) {
        Movie movie = new Movie(movieName, poster, trailerLink, releaseDate, durationMins,
                description, censor, genre, director, actors, language);
        createMovieController.createMovie(movie);
    }
}
