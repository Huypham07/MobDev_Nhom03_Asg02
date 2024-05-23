package com.example.user.controller.notification;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.user.model.Notification;
import com.example.user.utils.FirebaseUtils;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.function.Consumer;

public class GetNotificationController implements NotificationReader {
    @Override
    public void getNotification(String userId, Consumer<Notification> onNotificationRead) {
        FirebaseUtils.getDatabaseReference("Notifications").child(userId)
                .child("ticket_expiry_channel")
                .orderByChild("timestamp").addChildEventListener(
                        new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                Notification notification = snapshot.getValue(Notification.class);
                                if (notification != null) {
                                    onNotificationRead.accept(notification);
                                }
                            }

                            @Override
                            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                            }

                            @Override
                            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                            }

                            @Override
                            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        }
                );
    }
}
