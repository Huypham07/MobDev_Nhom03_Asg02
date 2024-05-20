package com.example.asg02.controller;


import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InfoController {
    private DatabaseReference databaseReference;
    private Context context;

    public InfoController(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void updateManagerNameByEmail(String email, String newName) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Managers");
        databaseReference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    snapshot.getRef().child("name").setValue(newName);
                    Toast.makeText(context, "Name updated successfully", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(context, "Manager with email not found", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, "Failed to update name: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
