package com.example.asg02.controller;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.asg02.model.Account;
import com.example.asg02.model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.AbstractMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class GetAccountController implements AccountReader {
    private FirebaseDatabase database;

    public GetAccountController() {
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public CompletableFuture<Account> getAccountWithPhone(String phone, String password) {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<Account> getAccountWithEmail(String email, String password) {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void getAllUser(Consumer<Map.Entry<String, User>> onUserAdded) {
        database.getReference("Users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                if (user != null) {
                    onUserAdded.accept(new AbstractMap.SimpleImmutableEntry<>(snapshot.getKey(), user));
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
}
