package com.example.user.controller.movie;

import com.example.user.model.Movie;
import com.example.user.utils.FirebaseUtils;

public class UpdateMovieController implements MovieUpdater {
    @Override
    public void updateMovie(Movie movie) {
        FirebaseUtils.getDatabaseReference("Movies").child(String.valueOf(movie.getId())).setValue(movie);
    }
}
