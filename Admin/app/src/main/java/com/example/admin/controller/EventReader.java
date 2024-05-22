package com.example.admin.controller;

import com.example.admin.model.Event;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface EventReader {
    CompletableFuture<List<Event>> getAllEvent();
}
