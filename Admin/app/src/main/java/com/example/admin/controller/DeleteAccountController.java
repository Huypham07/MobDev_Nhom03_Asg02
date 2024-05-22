package com.example.admin.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

        FirebaseUser manager = auth.getCurrentUser();
        Uid.append(manager.getUid());
        manager.delete().addOnCompleteListener(task1 -> {
            if (task1.isSuccessful()) {
                deleted = true;
            }
        });

        database.getReference("Managers").child(Uid.toString()).
                removeValue().addOnSuccessListener(aVoid -> {
            deleted = true;
        });
        return deleted;
    }
}
