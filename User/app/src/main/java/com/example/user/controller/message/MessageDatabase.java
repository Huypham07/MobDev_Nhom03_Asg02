package com.example.user.controller.message;

import com.example.user.model.Message;

import java.util.function.Consumer;

public interface MessageDatabase {
    void getMessages(String uID, Consumer<Message> onMessageAdded);
    void addMessage(Message message);
}
