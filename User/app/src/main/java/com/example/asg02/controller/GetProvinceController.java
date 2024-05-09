package com.example.asg02.controller;

import com.example.asg02.model.Province;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class GetProvinceController {
    private FirebaseDatabase database;

    public GetProvinceController() {
        database = FirebaseDatabase.getInstance();
    }

    public List<String> getAllProvinces() {
        List<String> provinces = new ArrayList<>();
        database.getReference("place").child("province").get().addOnSuccessListener(dataSnapshot -> {
            for (int i = 0; i < dataSnapshot.getChildrenCount(); i++) {
                Province p = dataSnapshot.child(String.valueOf(i)).getValue(Province.class);
                provinces.add(p.getName());
            }
        });
        return provinces;
    }
}
