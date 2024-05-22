package com.example.asg02;

import com.example.asg02.controller.CreateEventController;
import com.example.asg02.model.Event;

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

