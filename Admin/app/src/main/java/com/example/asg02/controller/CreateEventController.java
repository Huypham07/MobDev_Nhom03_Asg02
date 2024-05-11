package com.example.asg02.controller;

import android.content.Context;
import android.widget.Toast;

import com.example.asg02.AdminAddEventActivity;
import com.example.asg02.model.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.CompletableFuture;

public class CreateEventController implements EventCreator {
    private FirebaseDatabase database;
    private Context context;

    public CreateEventController(Context context) {
        database = FirebaseDatabase.getInstance();
        this.context = context;
    }

    @Override
    public void createEvent(Event event) {
        if (event == null) {
            return;
        }
        database.getReference("Events").child(String.valueOf(event.getId())).setValue(event)
                .addOnSuccessListener(aVoid -> Toast.makeText(context, "Thêm thành công", Toast.LENGTH_LONG))
                .addOnFailureListener(e -> Toast.makeText(context, "Thêm không thành công", Toast.LENGTH_LONG));
    }
}
