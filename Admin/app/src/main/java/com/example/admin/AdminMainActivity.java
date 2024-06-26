package com.example.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

public class AdminMainActivity extends AppCompatActivity {

    FrameLayout cinemaBtn;
    FrameLayout eventBtn;
    FrameLayout messageBtn;
    FrameLayout movieBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        cinemaBtn = findViewById(R.id.cinemaBtn);
        eventBtn = findViewById(R.id.eventBtn);
        messageBtn = findViewById(R.id.messageBtn);
        movieBtn = findViewById(R.id.movieBtn);

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

        movieBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AdminCreateMovieActivity.class);
            startActivity(intent);
        });
    }
}