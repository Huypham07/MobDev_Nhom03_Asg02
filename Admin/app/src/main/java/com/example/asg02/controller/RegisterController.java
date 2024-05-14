package com.example.asg02.controller;

import android.widget.ProgressBar;

import com.example.asg02.model.Account;
import com.example.asg02.model.Manager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.CompletableFuture;

public class RegisterController implements AccountCreator {
    ProgressBar progressBar;
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private boolean createSuccess = false;

    public RegisterController() {
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    public int register(Account account) {
        return createAccount(account);
    }

    @Override
    public CompletableFuture<Boolean> checkExistEmail(String email) {
        return CompletableFuture.completedFuture(false);
    }

    @Override
    public CompletableFuture<Boolean> checkExistPhone(String phone) {
        return CompletableFuture.completedFuture(false);
    }

    @Override
    public int createAccount(Account account) {
        if (account == null) {
            return FAIL;
        }
        Manager manager = (Manager) account;
        auth.createUserWithEmailAndPassword(manager.getEmail(), manager.getPassword())
                .addOnSuccessListener(authResult -> {
                    FirebaseUser currentUser = auth.getCurrentUser();
                    database.getReference("Managers")
                            .child(currentUser.getUid()).setValue(manager)
                            .addOnSuccessListener(aVoid -> createSuccess = true)
                            .addOnFailureListener(e -> createSuccess = false);
                })
                .addOnFailureListener(e -> createSuccess = false);
        return createSuccess ? SUCCESS : FAIL;
    }

}
