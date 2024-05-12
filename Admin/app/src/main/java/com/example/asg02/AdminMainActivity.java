package com.example.asg02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class AdminMainActivity extends AppCompatActivity {

    FrameLayout cinemaBtn;
    FrameLayout eventBtn;
    FrameLayout messageBtn;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        cinemaBtn = findViewById(R.id.cinemaBtn);
        eventBtn = findViewById(R.id.eventBtn);
        messageBtn = findViewById(R.id.messageBtn);

        cinemaBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AdminAddCinemaActivity.class);
            intent.putExtra("CinemaName", "");
            startActivity(intent);
        });

        eventBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AdminManageEventActivity.class);
            startActivity(intent);
        });

        messageBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AdminMessageActivity.class);
            startActivity(intent);
        });
    }
}