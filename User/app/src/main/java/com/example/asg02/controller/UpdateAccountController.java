package com.example.asg02.controller;

import com.example.asg02.model.Account;
import com.example.asg02.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.CompletableFuture;

public class UpdateAccountController implements AccountUpdater, AccountCreator {
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private boolean emailExists = false;
    private boolean phoneExists = false;

    public UpdateAccountController() {
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
    }

    public String sendCodeToEmail(String email) {
        return null;
    }

    public String sendCodeToPhone(String phone) {
        return null;
    }

    public boolean verifyCode(String expect, String actual) {
        return false;
    }

    @Override
    public int updateCurrentAccount(Account newAccount) {
        if (newAccount == null) {
            return FAIL;
        }

        User user = (User) newAccount;
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser == null) {
            return FAIL;
        }
        database.getReference().child("Users").child(currentUser.getUid()).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                User oldUser = task.getResult().getValue(User.class);
                if (oldUser == null) {
                    return;
                }
                if (!oldUser.getEmail().equals(user.getEmail())) {
                    checkExistEmail(user.getEmail()).thenApply(exist -> {
                        if (exist) {
                            emailExists = true;
                        }
                        return null;
                    });
                }

                if (!oldUser.getPhone().equals(user.getPhone())) {
                    checkExistPhone(user.getPhone()).thenApply(exist -> {
                        if (exist) {
                            phoneExists = true;
                        }
                        return null;
                    });
                }
            }
        });

        if (emailExists) {
            return EMAIL_EXISTS;
        } else if (phoneExists) {
            return PHONE_EXISTS;
        } else {
            currentUser.updateEmail(user.getEmail());
            auth.updateCurrentUser(currentUser);
            database.getReference("Users").child(currentUser.getUid()).setValue(user);
            return SUCCESS;
        }
    }

    @Override
    public CompletableFuture<Boolean> checkExistEmail(String email) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();

        database.getReference("Users").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                emailExists = false;
                for (DataSnapshot data : task.getResult().getChildren()) {
                    User u = data.getValue(User.class);
                    if (u.getEmail().equals(email)) {
                        emailExists = true;
                        break;
                    }
                }
                future.complete(emailExists);
            }
        });

        return future;
    }

    @Override
    public CompletableFuture<Boolean> checkExistPhone(String phone) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();

        database.getReference("Users").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                phoneExists = false;
                for (DataSnapshot data : task.getResult().getChildren()) {
                    User u = data.getValue(User.class);
                    if (u.getPhone().equals(phone)) {
                        phoneExists = true;
                        break;
                    }
                }
                future.complete(phoneExists);
            }
        });

        return future;
    }

    @Override
    public int createAccount(Account account) {
        return FAIL;
    }
}
