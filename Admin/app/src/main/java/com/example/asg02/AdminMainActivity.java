package com.example.asg02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

public class AdminMainActivity extends AppCompatActivity {

    FrameLayout cinemaBtn;
    FrameLayout eventBtn;
    FrameLayout messageBtn;
    FrameLayout historyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        cinemaBtn = findViewById(R.id.cinemaBtn);
        eventBtn = findViewById(R.id.eventBtn);
        messageBtn = findViewById(R.id.messageBtn);
        historyBtn = findViewById(R.id.historyBtn);

        cinemaBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AdminAddCinemaActivity.class);
            startActivity(intent);
        });

        eventBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AdminEventActivity.class);
            startActivity(intent);
        });

        messageBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AdminMessageActivity.class);
            startActivity(intent);
        });

        historyBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AdminHistoryActivity.class);
            startActivity(intent);
        });
    }
}