package com.example.asg02.controller;

import android.content.Context;
import android.widget.Toast;

import com.example.asg02.model.Show;
import com.google.firebase.database.FirebaseDatabase;

public class EditAndDeleteController {
    private FirebaseDatabase database;
    private Context context;
    public EditAndDeleteController(Context context) {
        database = FirebaseDatabase.getInstance();
        this.context = context;
    }

}
