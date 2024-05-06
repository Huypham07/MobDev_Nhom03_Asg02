package com.example.asg02.controller;

import androidx.annotation.NonNull;
import com.example.asg02.model.Account;
import com.example.asg02.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.jetbrains.annotations.NotNull;

public class RegisterController implements AccountDatabase {
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private boolean createSuccess = false;

    public RegisterController() {
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    public boolean register(Account account) {
        return createAccount(account);
    }
    @Override
    public boolean createAccount(Account account) {
        if (account == null) {
            return false;
        }

        if (account instanceof User) {
            User user = (User) account;
            auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser currentUser = auth.getCurrentUser();
                        database.getReference("Users")
                                .child(currentUser.getUid()).setValue(user)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            createSuccess = true;
                                        }
                                    }
                                });
                    }
                });
            return createSuccess;
        } else {
            return false;
        }
    }

    @Override
    public Account getAccountWithPhone(String phone, String password) {
        return null;
    }

    @Override
    public Account getAccountWithEmail(String email, String password) {
        return null;
    }

    @Override
    public boolean updateAccount(Account account) {
        return false;
    }

    @Override
    public boolean deleteAccount(Account account) {
        return false;
    }
}
