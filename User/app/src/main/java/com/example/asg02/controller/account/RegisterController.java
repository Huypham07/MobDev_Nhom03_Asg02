package com.example.asg02.controller.account;

import com.example.asg02.model.Account;
import com.example.asg02.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.CompletableFuture;

public class RegisterController implements AccountCreator {
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private boolean createSuccess = false;
    private boolean emailExists = false;
    private boolean phoneExists = false;

    public RegisterController() {
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
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
    public CompletableFuture<Integer> createAccount(Account account) {
        if (account == null) {
            return CompletableFuture.completedFuture(FAIL);
        }
        CompletableFuture<Integer> future = new CompletableFuture<>();
        User user = (User) account;
        checkExistEmail(user.getEmail()).thenAccept(emailExists_ -> {
            if (emailExists_) {
                future.complete(EMAIL_EXISTS);
            } else {
                checkExistPhone(user.getPhone()).thenAccept(phoneExists_ -> {
                    if (phoneExists_) {
                        future.complete(PHONE_EXISTS);
                    } else {
                        auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                                .addOnSuccessListener(authResult -> {
                                    FirebaseUser currentUser = auth.getCurrentUser();
                                    database.getReference("Users")
                                            .child(currentUser.getUid()).setValue(user)
                                            .addOnSuccessListener(aVoid -> future.complete(SUCCESS))
                                            .addOnFailureListener(e -> future.complete(FAIL));
                                })
                                .addOnFailureListener(e -> future.complete(FAIL));
                    }
                });
            }
        });

        return future;
    }

}
