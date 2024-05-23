package com.example.admin;

import com.example.admin.controller.CreateEventController;
import com.example.admin.model.Event;

public class CreateEventLogic {

    private final CreateEventController createEventController;

    public CreateEventLogic(CreateEventController createEventController) {
        this.createEventController = createEventController;
    }

    public void createEvent(String eventName, String posterUri, String startDate, String endDate, String eventInfo) {
        Event event = new Event(eventName, posterUri, startDate, endDate, eventInfo);
        createEventController.createEvent(event);
    }
}

