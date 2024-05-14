package com.example.asg02.controller;

import android.content.Context;
import android.widget.Toast;

import com.example.asg02.model.Cinema;
import com.example.asg02.model.Hall;
import com.example.asg02.model.Movie;
import com.example.asg02.model.Showtime;
import com.google.firebase.database.FirebaseDatabase;

public class CreateShowtimeController {
    private FirebaseDatabase database;
    private Context context;

    public CreateShowtimeController(Context context) {
        database = FirebaseDatabase.getInstance();
        this.context = context;
    }

    public void createShowtime(Showtime showtime) {
        if (showtime == null) {
            return;
        }
        database.getReference("Showtimes").child(String.valueOf(showtime.getId())).setValue(showtime)
                .addOnSuccessListener(aVoid -> Toast.makeText(context, "Thêm thành công", Toast.LENGTH_LONG))
                .addOnFailureListener(e -> Toast.makeText(context, "Thêm không thành công", Toast.LENGTH_LONG));
    }
}
