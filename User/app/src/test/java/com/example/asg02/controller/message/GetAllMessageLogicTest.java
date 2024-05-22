package com.example.asg02.controller.message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

import com.example.asg02.model.Message;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;

import java.util.function.Consumer;

public class GetAllMessageLogicTest {

    private MessageDatabase mockMessageDatabase;
    private GetAllMessageLogic getAllMessageLogic;

    @Before
    public void setUp() {
        mockMessageDatabase = mock(MessageDatabase.class);
        getAllMessageLogic = new GetAllMessageLogic(mockMessageDatabase);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetMessages() {
        // Prepare mock consumer
        Consumer<Message> mockConsumer = mock(Consumer.class);

        // Prepare mock message
        Message message1 = new Message("user1", "Hello, World!", System.currentTimeMillis());

        // Capture the consumer passed to the messageDatabase
        ArgumentCaptor<Consumer<Message>> consumerCaptor = ArgumentCaptor.forClass((Class) Consumer.class);
        doNothing().when(mockMessageDatabase).getMessages(anyString(), consumerCaptor.capture());

        // Call getMessages with mock consumer
        getAllMessageLogic.getMessages("user1", mockConsumer);

        // Simulate the onMessageAdded consumer being called with the message
        Consumer<Message> capturedConsumer = consumerCaptor.getValue();
        capturedConsumer.accept(message1);

        // Verify the consumer was called with the correct message
        verify(mockConsumer).accept(message1);
    }

    @Test
    public void testAddMessage() {
        // Prepare mock message
        Message message1 = new Message("user1", "Hello, World!", System.currentTimeMillis());

        // Call addMessage with the mock message
        getAllMessageLogic.addMessage(message1);

        // Verify the message was added to the database
        verify(mockMessageDatabase).addMessage(message1);
    }

    @Test
    public void testGetMessages_NoMessages() {
        // Prepare mock consumer
        Consumer<Message> mockConsumer = mock(Consumer.class);

        // Capture the consumer passed to the messageDatabase
        ArgumentCaptor<Consumer<Message>> consumerCaptor = ArgumentCaptor.forClass((Class) Consumer.class);
        doNothing().when(mockMessageDatabase).getMessages(anyString(), consumerCaptor.capture());

        // Call getMessages with mock consumer
        getAllMessageLogic.getMessages("user1", mockConsumer);

        // Simulate no messages being added
        Consumer<Message> capturedConsumer = consumerCaptor.getValue();

        // Verify the consumer was not called
        verify(mockConsumer, never()).accept(any(Message.class));
    }

    @Test
    public void testGetMessages_MultipleMessages() {
        // Prepare mock consumer
        Consumer<Message> mockConsumer = mock(Consumer.class);

        // Prepare mock messages
        Message message1 = new Message("user1", "Hello, World!", System.currentTimeMillis());
        Message message2 = new Message("user1", "Another message", System.currentTimeMillis());

        // Capture the consumer passed to the messageDatabase
        ArgumentCaptor<Consumer<Message>> consumerCaptor = ArgumentCaptor.forClass((Class) Consumer.class);
        doNothing().when(mockMessageDatabase).getMessages(anyString(), consumerCaptor.capture());

        // Call getMessages with mock consumer
        getAllMessageLogic.getMessages("user1", mockConsumer);

        // Simulate the onMessageAdded consumer being called with multiple messages
        Consumer<Message> capturedConsumer = consumerCaptor.getValue();
        capturedConsumer.accept(message1);
        capturedConsumer.accept(message2);

        // Verify the consumer was called with the correct messages
        verify(mockConsumer).accept(message1);
        verify(mockConsumer).accept(message2);
    }

    @Test
    public void testGetMessages_ErrorHandling() {
        // Prepare mock consumer
        Consumer<Message> mockConsumer = mock(Consumer.class);

        // Simulate an error during message retrieval
        doThrow(new RuntimeException("Database error")).when(mockMessageDatabase).getMessages(anyString(), any(Consumer.class));

        try {
            // Call getMessages with mock consumer
            getAllMessageLogic.getMessages("user1", mockConsumer);
            fail("Expected an exception to be thrown");
        } catch (RuntimeException e) {
            // Verify that the exception message is as expected
            assertEquals("Database error", e.getMessage());
        }

        // Verify the consumer was not called
        verify(mockConsumer, never()).accept(any(Message.class));
    }
}
