package com.example.asg02.controller;

import com.example.asg02.model.Event;

import java.util.concurrent.CompletableFuture;

public interface EventCreator {
    void createEvent(Event event);
}
