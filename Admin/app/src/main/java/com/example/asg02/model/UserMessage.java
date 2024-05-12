package com.example.asg02.model;

public class UserMessage extends Message {
    public UserMessage(String message, Time time, Account sender, Account receiver) {
        super(message, time, sender, receiver);
    }
}
