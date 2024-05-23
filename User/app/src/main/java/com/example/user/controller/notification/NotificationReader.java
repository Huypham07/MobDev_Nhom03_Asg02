package com.example.user.controller.notification;

import com.example.user.model.Notification;

import java.util.function.Consumer;

public interface NotificationReader {
    void getNotification(String userId, Consumer<Notification> onNotificationRead);
}
