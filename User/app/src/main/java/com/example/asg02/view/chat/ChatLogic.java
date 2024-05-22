package com.example.asg02.view.chat;

import com.example.asg02.controller.message.GetAllMessageController;
import com.example.asg02.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ChatLogic {
    private GetAllMessageController messageController;
    private String userId;
    private List<Message> messages = new ArrayList<>();
    private Consumer<Message> onMessageReceived;

    public ChatLogic(String userId, GetAllMessageController messageController, Consumer<Message> onMessageReceived) {
        if (userId == null) {
            throw new IllegalArgumentException("User id must not be null");
        }
        this.userId = userId;
        this.messageController = messageController;
        this.onMessageReceived = onMessageReceived;
    }

    public void loadMessages() {
        messageController.getMessages(userId, message -> {
            messages.add(message);
            onMessageReceived.accept(message);
        });
    }

    public void sendMessage(String text) {
        if (text == null || text.isEmpty()) {
            return;
        }
        Message message = new Message(userId, text, System.currentTimeMillis());
        messageController.addMessage(message);
    }

    public List<Message> getMessages() {
        return messages;
    }
}
