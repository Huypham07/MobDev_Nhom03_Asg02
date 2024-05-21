package com.example.asg02.controller.message;

import androidx.annotation.NonNull;

import com.example.asg02.model.Message;
import com.example.asg02.utils.FirebaseUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.function.Consumer;

public class GetAllMessageController implements MessageDatabase {

    public GetAllMessageController() {
    }

    @Override
    public void getMessages(String uID, Consumer<Message> onMessageAdded) {
        FirebaseUtils.getDatabaseReference("Messages").child(uID)
                .orderByChild("timestamp").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Message message = snapshot.getValue(Message.class);
                            if (message != null) {
                                onMessageAdded.accept(message);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    @Override
    public void addMessage(Message message) {

    }
}
