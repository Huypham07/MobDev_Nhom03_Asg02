package com.example.asg02.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtils {
    public static DatabaseReference getDatabaseReference(String path) {
        return FirebaseDatabase.getInstance().getReference(path);
    }
    // Add more Firebase related utility methods here
}
