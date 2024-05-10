package com.example.asg02.controller;

import com.example.asg02.model.Event;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetEventController implements EventReader {

    FirebaseDatabase database;


    public GetEventController() {
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public CompletableFuture<List<Event>> getAllEvent() {
        CompletableFuture<List<Event>> future = new CompletableFuture<>();

        database.getReference("Events").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                List<Event> events = new ArrayList<>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Event e = (Event) data.getValue(Event.class);
                    if (e != null) {
                        events.add(e);
                    }
                }
                future.complete(events);
            }
        });

        return future;
    }
}
