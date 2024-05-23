package com.example.user.controller.notification;

import com.example.user.model.Notification;
import com.example.user.utils.FirebaseUtils;

public class UpdateNotificationController implements NotificationUpdater {
    @Override
    public void upadateNotification(Notification notification) {
        FirebaseUtils.getDatabaseReference("Notifications").child(notification.getUserId())
                .child("ticket_expiry_channel")
                .child(String.valueOf(notification.getId())).setValue(notification);
    }
}
