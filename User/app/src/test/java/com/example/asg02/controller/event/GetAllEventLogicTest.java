package com.example.asg02.controller.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

import com.example.asg02.model.Event;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;

import java.util.function.Consumer;

public class GetAllEventLogicTest {

    private EventReader mockEventReader;
    private GetAllEventLogic getAllEventLogic;

    @Before
    public void setUp() {
        mockEventReader = mock(EventReader.class);
        getAllEventLogic = new GetAllEventLogic(mockEventReader);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEvents() {
        // Prepare mock consumer
        Consumer<Event> mockConsumer = mock(Consumer.class);

        // Prepare mock event
        Event event1 = new Event(1, "Event 1", "01/01/2024", "02/01/2024", "Info 1", "Poster 1");

        // Capture the consumer passed to the eventReader
        ArgumentCaptor<Consumer<Event>> consumerCaptor = ArgumentCaptor.forClass((Class) Consumer.class);
        doNothing().when(mockEventReader).getAllEvents(consumerCaptor.capture());

        // Call getAllEvents with mock consumer
        getAllEventLogic.getAllEvents(mockConsumer);

        // Simulate the onEventAdded consumer being called with the event
        Consumer<Event> capturedConsumer = consumerCaptor.getValue();
        capturedConsumer.accept(event1);

        // Verify the consumer was called with the correct event
        verify(mockConsumer).accept(event1);
    }

    @Test
    public void testGetAllEvents_NoEvents() {
        // Prepare mock consumer
        Consumer<Event> mockConsumer = mock(Consumer.class);

        // Capture the consumer passed to the eventReader
        ArgumentCaptor<Consumer<Event>> consumerCaptor = ArgumentCaptor.forClass((Class) Consumer.class);
        doNothing().when(mockEventReader).getAllEvents(consumerCaptor.capture());

        // Call getAllEvents with mock consumer
        getAllEventLogic.getAllEvents(mockConsumer);

        // Simulate no events being added
        Consumer<Event> capturedConsumer = consumerCaptor.getValue();

        // Verify the consumer was not called
        verify(mockConsumer, never()).accept(any(Event.class));
    }

    @Test
    public void testGetAllEvents_MultipleEvents() {
        // Prepare mock consumer
        Consumer<Event> mockConsumer = mock(Consumer.class);

        // Prepare mock events
        Event event1 = new Event(1, "Event 1", "01/01/2024", "02/01/2024", "Info 1", "Poster 1");
        Event event2 = new Event(2, "Event 2", "03/01/2024", "04/01/2024", "Info 2", "Poster 2");

        // Capture the consumer passed to the eventReader
        ArgumentCaptor<Consumer<Event>> consumerCaptor = ArgumentCaptor.forClass((Class) Consumer.class);
        doNothing().when(mockEventReader).getAllEvents(consumerCaptor.capture());

        // Call getAllEvents with mock consumer
        getAllEventLogic.getAllEvents(mockConsumer);

        // Simulate the onEventAdded consumer being called with multiple events
        Consumer<Event> capturedConsumer = consumerCaptor.getValue();
        capturedConsumer.accept(event1);
        capturedConsumer.accept(event2);

        // Verify the consumer was called with the correct events
        verify(mockConsumer).accept(event1);
        verify(mockConsumer).accept(event2);
    }

    @Test
    public void testGetAllEvents_ErrorHandling() {
        // Prepare mock consumer
        Consumer<Event> mockConsumer = mock(Consumer.class);

        // Simulate an error during event retrieval
        doThrow(new RuntimeException("Database error")).when(mockEventReader).getAllEvents(any(Consumer.class));

        try {
            // Call getAllEvents with mock consumer
            getAllEventLogic.getAllEvents(mockConsumer);
            fail("Expected an exception to be thrown");
        } catch (RuntimeException e) {
            // Verify that the exception message is as expected
            assertEquals("Database error", e.getMessage());
        }

        // Verify the consumer was not called
        verify(mockConsumer, never()).accept(any(Event.class));
    }
}
