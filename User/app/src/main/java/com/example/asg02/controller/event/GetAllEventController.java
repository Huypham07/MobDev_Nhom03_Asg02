package com.example.asg02.controller.event;

import com.example.asg02.model.Event;
import com.example.asg02.utils.FirebaseUtils;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetAllEventController implements EventReader {

    public GetAllEventController() {
    }

    @Override
    public CompletableFuture<List<Event>> getAllEvents() {
        CompletableFuture<List<Event>> future = new CompletableFuture<>();
        FirebaseUtils.getDatabaseReference("Events").get().addOnSuccessListener(dataSnapshot -> {
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
