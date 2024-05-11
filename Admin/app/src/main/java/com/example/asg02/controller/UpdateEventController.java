package com.example.asg02.controller;

import android.widget.Toast;

import com.example.asg02.model.Event;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateEventController implements EventUpdate{
    private FirebaseDatabase database;
    private boolean updated = false;
    public UpdateEventController() {
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public boolean updateEvent(Event event) {
        if (event == null) {
            return updated;
        }
        database.getReference("Events").child(String.valueOf(event.getId())).setValue(event)
                .addOnSuccessListener(aVoid -> updated = true);
        return updated;
    }
}
