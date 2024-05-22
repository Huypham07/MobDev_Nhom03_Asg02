package com.example.asg02.controller.message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.asg02.model.Message;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.function.Consumer;

public class GetAllMessageController implements MessageDatabase {

    private FirebaseDatabase database;
    public GetAllMessageController() {
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public void getMessages(String uID, Consumer<Message> onMessageAdded) {
        database.getReference("Messages").child(uID)
                .orderByChild("timestamp").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Message message = snapshot.getValue(Message.class);
                        if (message != null) {
                            onMessageAdded.accept(message);
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
                });
    }


    @Override
    public void addMessage(Message message) {
        if (message == null) return;
        database.getReference("Messages")
                .child(message.getSenderId()).push().setValue(message);
    }

    @Override
    public void addMessage(Message message, String uID) {
        if (message == null) return;
        database.getReference("Messages")
                .child(uID).push().setValue(message);
    }
}
