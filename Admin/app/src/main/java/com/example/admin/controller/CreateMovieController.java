package com.example.admin.controller;

import android.content.Context;
import android.widget.Toast;

import com.example.admin.model.Movie;
import com.google.firebase.database.FirebaseDatabase;

public class CreateMovieController {
    private FirebaseDatabase database;
    private Context context;

    public CreateMovieController(Context context) {
        database = FirebaseDatabase.getInstance();
        this.context = context;
    }

    public void createMovie(Movie movie) {
        if (movie == null) {
            return;
        }
        database.getReference("Movies").child(String.valueOf(movie.getId())).setValue(movie)
                .addOnSuccessListener(aVoid -> Toast.makeText(context, "Thêm thành công", Toast.LENGTH_LONG))
                .addOnFailureListener(e -> Toast.makeText(context, "Thêm không thành công", Toast.LENGTH_LONG));
    }
}
