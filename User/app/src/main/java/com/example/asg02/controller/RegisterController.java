package com.example.asg02.controller;

import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.example.asg02.model.Account;
import com.example.asg02.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class RegisterController implements AccountCreator {
    ProgressBar progressBar;
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private boolean createSuccess = false;
    private boolean emailExists = false;
    private boolean phoneExists = false;

    public RegisterController(ProgressBar progressBar) {
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        this.progressBar = progressBar;
    }

    public int register(Account account) {
        return createAccount(account);
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
        if (account == null) {
            return FAIL;
        }
        User user = (User) account;
        checkExistEmail(user.getEmail()).thenApply(exist -> {
            if (exist) {
                emailExists = true;
            } else {
                checkExistPhone(user.getPhone()).thenApply(exist_ -> {
                    if (exist_) {
                        phoneExists = true;
                    } else {
                        auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                                .addOnSuccessListener(authResult -> {
                                    FirebaseUser currentUser = auth.getCurrentUser();
                                    database.getReference("Users")
                                            .child(currentUser.getUid()).setValue(user)
                                            .addOnSuccessListener(aVoid -> {
                                                createSuccess = true;
                                            })
                                            .addOnFailureListener(e -> {
                                                createSuccess = false;
                                            });
                                })
                                .addOnFailureListener(e -> {
                                    createSuccess = false;});
                    }
                    return null;
                });
            }
            return null;
        });

        if (emailExists) {
            return EMAIL_EXISTS;
        }

        if (phoneExists) {
            return PHONE_EXISTS;
        }

        return createSuccess ? SUCCESS : FAIL;
    }

}
