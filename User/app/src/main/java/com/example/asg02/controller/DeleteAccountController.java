package com.example.asg02.controller;

import com.example.asg02.model.Account;
import com.example.asg02.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

public class DeleteAccountController implements AccountDeleter {
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private boolean deleted = false;

    public DeleteAccountController() {
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    public boolean deleteAccount() {
        StringBuilder Uid = new StringBuilder();
        deleted = false;
        FirebaseUser user = auth.getCurrentUser();
        Uid.append(user.getUid());
        user.delete().addOnCompleteListener(task1 -> {
            if (task1.isSuccessful()) {
                deleted = true;
            }
        });

        database.getReference("Users").child(Uid.toString()).
                removeValue().addOnSuccessListener(aVoid -> {
            deleted = true;
        });
        return deleted;
    }
}
