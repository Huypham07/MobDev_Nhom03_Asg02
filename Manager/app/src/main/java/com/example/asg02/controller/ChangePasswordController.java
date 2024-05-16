package com.example.asg02.controller;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ChangePasswordController {
    private FirebaseDatabase database;
    private Context context;
    public ChangePasswordController(Context context) {
        database = FirebaseDatabase.getInstance();
        this.context = context;
    }

    public void changePassword(String newPassword) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        user.updatePassword(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Mật khẩu đã được cập nhật thành công trong Firebase Authentication
                            // Tiến hành cập nhật mật khẩu trong Realtime Database

                            String userId = user.getUid(); // Lấy ID của người dùng
                            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Managers").child(userId);

                            // Cập nhật mật khẩu trong Realtime Database
                            userRef.child("password").setValue(newPassword)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                // Mật khẩu đã được cập nhật thành công trong Realtime Database
                                                Toast.makeText(context, "Password updated successfully", Toast.LENGTH_SHORT).show();
                                            } else {
                                                // Cập nhật mật khẩu thất bại trong Realtime Database
                                                Toast.makeText(context, "Failed to update password in Realtime Database", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        } else {
                            // Cập nhật mật khẩu thất bại trong Firebase Authentication
                            Toast.makeText(context, "Failed to update password in Authentication", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
