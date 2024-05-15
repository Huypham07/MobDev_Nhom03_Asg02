package com.example.asg02.event;

import com.example.asg02.model.Event;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetAllEventController implements EventReader {
    private FirebaseDatabase database;

    public GetAllEventController() {
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public CompletableFuture<List<Event>> getAllEvents() {
        CompletableFuture<List<Event>> future = new CompletableFuture<>();
        database.getReference("Events").get().addOnSuccessListener(dataSnapshot -> {
            List<Event> events = new ArrayList<>();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Event event = snapshot.getValue(Event.class);
                events.add(event);
            }
            future.complete(events);
        });
        return future;
    }
}
