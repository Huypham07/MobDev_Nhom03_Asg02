package com.example.asg02.controller.account;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.asg02.model.Account;
import com.example.asg02.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.CompletableFuture;

public class AuthenticaionController {
    FirebaseDatabase database;
    FirebaseAuth auth;
    public AuthenticaionController() {
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
    }

    private String verificationId;
    public void sendVerificationCode(String email) {
        auth.sendPasswordResetEmail(email);
    }

    public CompletableFuture<String> getEmailFromPhone(String phone) {
        CompletableFuture<String> future = new CompletableFuture<>();
        // get email from phone
        database.getReference("Users").get().addOnSuccessListener(dataSnapshot -> {
            for (DataSnapshot data : dataSnapshot.getChildren()) {
                User u = data.getValue(User.class);
                if (u.getPhone().equals(phone)) {
                    future.complete(u.getEmail());
                }
            }
        });
        return future;
    }

}
