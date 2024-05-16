package com.example.asg02.controller;

import android.content.Context;
import android.widget.Toast;

import com.example.asg02.model.Cinema;
import com.example.asg02.model.Hall;
import com.example.asg02.model.Movie;
import com.google.firebase.database.FirebaseDatabase;

public class CreateHallController {
    private FirebaseDatabase database;
    private Context context;

    public CreateHallController(Context context) {
        database = FirebaseDatabase.getInstance();
        this.context = context;
    }

    public void createHall(Hall hall) {
        if (hall == null) {
            return;
        }
        database.getReference("Halls").child(String.valueOf(hall.getId())).setValue(hall)
                .addOnSuccessListener(aVoid -> Toast.makeText(context, "Thêm thành công", Toast.LENGTH_LONG))
                .addOnFailureListener(e -> Toast.makeText(context, "Thêm không thành công", Toast.LENGTH_LONG));
    }
}
