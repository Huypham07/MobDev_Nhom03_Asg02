package com.example.asg02.controller.province;

import com.example.asg02.model.Province;
import com.google.firebase.database.*;
import java.util.concurrent.CompletableFuture;
import java.util.ArrayList;
import java.util.List;

public class GetProvinceLogic {
    private final DatabaseReference databaseReference;

    public GetProvinceLogic(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    public CompletableFuture<List<String>> getAllProvinces() {
        CompletableFuture<List<String>> future = new CompletableFuture<>();
        databaseReference.child("place").child("province").get().addOnSuccessListener(dataSnapshot -> {
            List<String> provinces = new ArrayList<>();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Province p = snapshot.getValue(Province.class);
                if (p != null) {
                    provinces.add(p.getName());
                }
            }
            future.complete(provinces);
        }).addOnFailureListener(future::completeExceptionally);
        return future;
    }
}

