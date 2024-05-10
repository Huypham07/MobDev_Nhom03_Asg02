package com.example.asg02.controller;

import android.widget.Toast;

import com.example.asg02.model.Event;
import com.google.firebase.database.FirebaseDatabase;

public class DeleteEventController implements EventDeleter{
    private FirebaseDatabase database;
    private boolean deleted = false;

    public DeleteEventController() {
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public boolean deleteEvent(Event event) {
        if (event == null) {
            return deleted;
        }
        database.getReference("Events").child(String.valueOf(event.hashCode())).removeValue()
                .addOnSuccessListener(aVoid -> deleted = true);
        return deleted;
    }
}
