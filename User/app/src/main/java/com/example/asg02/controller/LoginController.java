package com.example.asg02.controller;

import androidx.annotation.NonNull;
import com.example.asg02.model.Account;
import com.example.asg02.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import org.jetbrains.annotations.NotNull;

public class LoginController implements AccountDatabase {
    private FirebaseDatabase database;
    private FirebaseAuth auth;

public LoginController() {
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    public Account login(String id, String password) {
        if (idIsEmail(id)) {
            return getAccountWithEmail(id, password);
        } else {
            return getAccountWithPhone(id, password);
        }
    }

    private boolean idIsEmail(String id) {
        return id.contains("@");
    }
    @Override
    public boolean createAccount(Account account) {
        return false;
    }

    @Override
    public Account getAccountWithPhone(String phone, String password) {
        //
        final User[] user = new User[1];
        database.getReference("Users").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    User u = data.getValue(User.class);
                    if (u.getPhone().equals(phone)) {
                        // return account
                        user[0] = u;
                    }
                }
            }
        });

        if (user[0] == null) {
            return null;
        }
        String email = user[0].getEmail();

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //
                } else {
                    // return null
                }
            }
        });
        return user[0];
    }

    @Override
    public Account getAccountWithEmail(String email, String password) {
        try {
            final User[] users = new User[1];
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String uID = auth.getCurrentUser().getUid();
                                database.getReference("Users").child(uID).get()
                                        .addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                                            @Override
                                            public void onSuccess(DataSnapshot dataSnapshot) {
                                                users[0] = dataSnapshot.getValue(User.class);
                                                // return account
                                            }
                                        });
                            } else {
                                // return null
                            }
                        }
                    });
            return users[0];
        } catch (NullPointerException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
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
