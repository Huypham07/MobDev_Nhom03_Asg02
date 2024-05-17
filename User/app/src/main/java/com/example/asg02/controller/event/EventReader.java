package com.example.asg02.controller.event;

import com.example.asg02.model.Event;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface EventReader {
    CompletableFuture<List<Event>> getAllEvents();
}
