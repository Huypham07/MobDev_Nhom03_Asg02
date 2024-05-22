package com.example.user.controller.event;

import com.example.user.model.Event;

import java.util.function.Consumer;

public class GetAllEventLogic {
    private final EventReader eventReader;

    public GetAllEventLogic(EventReader eventReader) {
        this.eventReader = eventReader;
    }

    public void getAllEvents(Consumer<Event> onEventAdded) {
        eventReader.getAllEvents(onEventAdded);
    }
}
