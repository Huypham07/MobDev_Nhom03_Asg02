package com.example.asg02.model;

public class AdminMessage extends Message{
    public AdminMessage(String message, Time time, Account sender, Account receiver) {
        super(message, time, sender, receiver);
    }
}
