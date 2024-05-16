package com.example.asg02.controller;

import android.content.Context;
import android.widget.Toast;

import com.example.asg02.ManagerActivity;
import com.example.asg02.model.Show;
import com.google.firebase.database.FirebaseDatabase;

public class CreateShowController {
    private FirebaseDatabase database;
    private Context context;

    public CreateShowController(Context context) {
        database = FirebaseDatabase.getInstance();
        this.context = context;
    }

    public void createShow(Show show) {
        if (show == null) {
            return;
        }
        database.getReference("Shows").child(String.valueOf(show.getId())).setValue(show)
                .addOnSuccessListener(aVoid -> Toast.makeText(context, "Thêm thành công", Toast.LENGTH_LONG))
                .addOnFailureListener(e -> Toast.makeText(context, "Thêm không thành công", Toast.LENGTH_LONG));
    }
}
