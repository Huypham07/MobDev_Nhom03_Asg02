package com.example.asg02.controller.account;

import android.util.Log;

import com.example.asg02.model.Account;
import com.example.asg02.model.Manager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetAllManagerController {
    private FirebaseDatabase database;

    public GetAllManagerController() {
        database = FirebaseDatabase.getInstance();
    }
    public CompletableFuture<List<Account>> getAllManagers() {
        CompletableFuture<List<Account>> future = new CompletableFuture<>();
        // get all managers
        database.getReference("Managers").get().addOnSuccessListener(dataSnapshot -> {
            List<Account> managers = new ArrayList<>();
            for (DataSnapshot data : dataSnapshot.getChildren()) {
                Manager m = data.getValue(Manager.class);
                managers.add(m);
            }
            future.complete(managers);
        });
        return future;
    }
}
