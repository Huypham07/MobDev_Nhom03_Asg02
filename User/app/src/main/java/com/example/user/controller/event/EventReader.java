package com.example.user.controller.event;

import com.example.user.model.Event;

import java.util.function.Consumer;

public interface EventReader {
    void getAllEvents(Consumer<Event> onEventAdded);
}
