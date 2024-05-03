package com.example.asg02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagerActivity extends AppCompatActivity {

    Button editInfoBution, moviesInMyCinemaButtion, cinemaSystemButton,
            addNewCinemaButton, addNewMovieButton, addNewShowtimeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        editInfoBution = findViewById(R.id.editInfo);
        moviesInMyCinemaButtion = findViewById(R.id.moviesInMyCinema);

        editInfoBution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });
        moviesInMyCinemaButtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerActivity.this, MoviesInCinemaActivity.class);
                startActivity(intent);
            }
        });
    }
}