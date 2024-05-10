package com.example.asg02.controller;

import com.example.asg02.model.Account;
import com.example.asg02.model.Event;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface EventReader {
    CompletableFuture<List<Event>> getAllEvent();
}
