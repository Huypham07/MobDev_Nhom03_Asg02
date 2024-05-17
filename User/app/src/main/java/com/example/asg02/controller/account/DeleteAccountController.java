package com.example.asg02.controller.account;

import com.example.asg02.utils.FirebaseUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DeleteAccountController implements AccountDeleter {
    private FirebaseAuth auth;
    private boolean deleted = false;

    public DeleteAccountController() {
        auth = FirebaseAuth.getInstance();
    }

    public boolean deleteAccount() {
        StringBuilder Uid = new StringBuilder();
        deleted = false;
        FirebaseUser user = auth.getCurrentUser();
        Uid.append(user.getUid());
        user.delete().addOnCompleteListener(task1 -> {
            if (task1.isSuccessful()) {
                deleted = true;
            }
        });

        FirebaseUtils.getDatabaseReference("Users").child(Uid.toString()).
                removeValue().addOnSuccessListener(aVoid -> {
            deleted = true;
        });
        return deleted;
    }
}
