package com.example.asg02.controller;

import android.content.Context;
import android.widget.Toast;

import com.example.asg02.model.Cinema;
import com.example.asg02.model.Movie;
import com.google.firebase.database.FirebaseDatabase;

public class CreateCinemaController {
    private FirebaseDatabase database;
    private Context context;

    public CreateCinemaController(Context context) {
        database = FirebaseDatabase.getInstance();
        this.context = context;
    }

    public void createCinema(Cinema cinema) {
        if (cinema == null) {
            return;
        }
        database.getReference("Cinemas").child(String.valueOf(cinema.getId())).setValue(cinema)
                .addOnSuccessListener(aVoid -> Toast.makeText(context, "Thêm thành công", Toast.LENGTH_LONG))
                .addOnFailureListener(e -> Toast.makeText(context, "Thêm không thành công", Toast.LENGTH_LONG));
    }
}
