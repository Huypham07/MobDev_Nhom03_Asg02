package com.example.asg02.controller;

import com.example.asg02.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class GetMovieController {
    public List<Movie> getAllCurrentMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Shawshank Redemption", 9.3f, 142,
                "20/05/2024", "No description", "No info", "English"));
        movies.add(new Movie("The Godfather", 9.2f, 175,
                "20/05/2024", "No description", "No info", "English"));
        movies.add(new Movie("The Dark Knight", 9.0f, 152, "20/05/2024",
                "No description", "No info", "English"));
        movies.add(new Movie("12 Angry", 8.9f, 96, "20/05/2024",
                "No description", "No info", "English"));

        return movies;
    }

    public List<Movie> getAllUpcomingMovies() {

        return null;
    }
}
