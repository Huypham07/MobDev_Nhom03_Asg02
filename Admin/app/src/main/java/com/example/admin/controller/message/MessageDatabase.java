package com.example.admin.controller.message;

import com.example.admin.model.Message;

import java.util.function.Consumer;

public interface MessageDatabase {
    void getMessages(String uID, Consumer<Message> onMessageAdded);
    void addMessage(Message message);
    void addMessage(Message message, String uID);
}
