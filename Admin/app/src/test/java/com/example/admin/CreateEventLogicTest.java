package com.example.admin;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.example.admin.controller.CreateEventController;
import com.example.admin.model.Event;

public class CreateEventLogicTest {
    private CreateEventController mockController;
    private CreateEventLogic addEventLogic;

    @Before
    public void setUp() {
        mockController = mock(CreateEventController.class);
        addEventLogic = new CreateEventLogic(mockController);
    }

    @Test
    public void createEvent_Successfully() {
        // Action
        addEventLogic.createEvent("Test Event", "posterUri", "startDate", "endDate", "eventInfo");

        // Verify
        verify(mockController).createEvent(any(Event.class));
    }

    @Test
    public void createEvent_WithNullEventName() {
        addEventLogic.createEvent(null, "posterUri", "startDate", "endDate", "eventInfo");

        verify(mockController, never()).createEvent(new Event());
    }

    @Test
    public void createEvent_WithNullPosterUri() {
        addEventLogic.createEvent("Test Event", null, "startDate", "endDate", "eventInfo");

        verify(mockController, never()).createEvent(new Event());
    }

    @Test
    public void createEvent_WithNullStartDate() {
        addEventLogic.createEvent("Test Event", "posterUri", null, "endDate", "eventInfo");

        verify(mockController, never()).createEvent(new Event());
    }

    @Test
    public void createEvent_WithNullEndDate() {
        addEventLogic.createEvent("Test Event", "posterUri", "startDate", null, "eventInfo");

        verify(mockController, never()).createEvent(new Event());
    }

    @Test
    public void createEvent_WithNullEventInfo() {
        addEventLogic.createEvent("Test Event", "posterUri", "startDate", "endDate", null);

        verify(mockController, never()).createEvent(new Event());
    }
}
