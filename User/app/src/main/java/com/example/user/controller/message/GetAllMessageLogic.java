package com.example.user.controller.message;

import com.example.user.model.Message;

import java.util.function.Consumer;

public class GetAllMessageLogic {
    private final MessageDatabase messageDatabase;

    public GetAllMessageLogic(MessageDatabase messageDatabase) {
        this.messageDatabase = messageDatabase;
    }

    public void getMessages(String uID, Consumer<Message> onMessageAdded) {
        messageDatabase.getMessages(uID, onMessageAdded);
    }

    public void addMessage(Message message) {
        messageDatabase.addMessage(message);
    }
}
