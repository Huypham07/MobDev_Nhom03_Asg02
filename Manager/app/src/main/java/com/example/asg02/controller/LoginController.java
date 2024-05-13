package com.example.asg02.controller;

import android.util.Log;

import androidx.annotation.NonNull;
import com.example.asg02.model.Account;
import com.example.asg02.model.Manager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class LoginController implements AccountReader {
    private FirebaseDatabase database;
    private FirebaseAuth auth;

    public LoginController() {
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    public CompletableFuture<Account> login(String id, String password) {
        if (id == null || password == null) {
            return null;
        }
//        if (idIsEmail(id)) {
//
//        }
//        else {
//            return getAccountWithPhone(id, password);
//        }
        return getAccountWithEmail(id, password);
    }

    private boolean idIsEmail(String id) {
        return id.contains("@");
    }

//    @Override
//    public CompletableFuture<Account> getAccountWithPhone(String phone, String password) {
//        CompletableFuture<Account> future = new CompletableFuture<>();
//        database.getReference("Managers").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
//            @Override
//            public void onSuccess(DataSnapshot dataSnapshot) {
//                for (DataSnapshot data : dataSnapshot.getChildren()) {
//                    Manager u = data.getValue(User.class);
//                    if (u.getPhone().equals(phone)) {
//                        // return account
//                        auth.signInWithEmailAndPassword(u.getEmail(), password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//                                    future.complete(u);
//                                } else {
//                                    // return null
//                                }
//                            }
//                        });
//                    }
//                }
//            }
//        });
//        return future;
//    }

    @Override
    public CompletableFuture<Account> getAccountWithEmail(String email, String password) {
        CompletableFuture<Account> future = new CompletableFuture<>();
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String uID = auth.getCurrentUser().getUid();
                            database.getReference("Managers").child(uID).get()
                                    .addOnCompleteListener(task_ -> future.complete(task_.getResult().getValue(Manager.class)));
                        } else {
                            // return null
                        }
                    }
                });
        return future;
    }
}