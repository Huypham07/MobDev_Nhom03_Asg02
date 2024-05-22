package com.example.asg02.view.chat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import com.example.asg02.controller.message.GetAllMessageController;
import com.example.asg02.model.Message;

public class ChatLogicTest {
    private GetAllMessageController mockMessageController;

    @Captor
    private ArgumentCaptor<Consumer<Message>> messageConsumerCaptor;

    private ChatLogic chatLogic;
    private List<Message> receivedMessages;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMessageController = mock(GetAllMessageController.class);
        receivedMessages = new ArrayList<>();
        chatLogic = new ChatLogic("user1", mockMessageController, receivedMessages::add);
    }

    @Test
    public void testLoadMessages() {
        // Prepare mock data
        Message message = new Message("user1", "Hello", System.currentTimeMillis());

        // Mock the behavior
        doNothing().when(mockMessageController).getMessages(eq("user1"), messageConsumerCaptor.capture());

        // Load messages
        chatLogic.loadMessages();

        // Simulate message reception
        messageConsumerCaptor.getValue().accept(message);

        // Verify
        assertEquals(1, chatLogic.getMessages().size());
        assertEquals(message, chatLogic.getMessages().get(0));
        assertEquals(1, receivedMessages.size());
        assertEquals(message, receivedMessages.get(0));
    }

    @Test
    public void testSendMessage() {
        // Prepare mock data
        String text = "Hello";

        // Send message
        chatLogic.sendMessage(text);

        // Verify
        verify(mockMessageController, times(1)).addMessage(any(Message.class));
    }

    @Test
    public void testSendMessageEmpty() {
        // Send empty message
        chatLogic.sendMessage("");

        // Verify
        verify(mockMessageController, never()).addMessage(any(Message.class));
    }

    @Test
    public void testSendMessageNull() {
        // Send null message
        chatLogic.sendMessage(null);

        // Verify
        verify(mockMessageController, never()).addMessage(any(Message.class));
    }
}
