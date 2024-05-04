package com.example.asg02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagerActivity extends AppCompatActivity {

    Button editInfoBution, moviesInMyCinemaButtion, cinemaSystemButton,
            addNewCinemaButton, addNewMovieButton, addNewShowtimeButton, addNewHallButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        editInfoBution = findViewById(R.id.editInfo);
        moviesInMyCinemaButtion = findViewById(R.id.moviesInMyCinema);
        cinemaSystemButton = findViewById(R.id.cinemaSystem);
        addNewCinemaButton = findViewById(R.id.addNewCinema);
        addNewMovieButton = findViewById(R.id.addNewMovie);
        addNewShowtimeButton = findViewById(R.id.addNewShowtime);
        addNewHallButton = findViewById(R.id.addNewHall);
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
        cinemaSystemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerActivity.this, CinemaSystemActivity.class);
                startActivity(intent);
            }
        });
        addNewCinemaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerActivity.this, ManagerAddCinemaActivity.class);
                startActivity(intent);
            }
        });
        addNewMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerActivity.this, ManagerAddMovieActivity.class);
                startActivity(intent);
            }
        });
        addNewShowtimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerActivity.this, ManagerAddShowtimeActivity.class);
                startActivity(intent);
            }
        });
        addNewHallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerActivity.this, ManagerAddHallActivity.class);
                startActivity(intent);
            }
        });
    }
}