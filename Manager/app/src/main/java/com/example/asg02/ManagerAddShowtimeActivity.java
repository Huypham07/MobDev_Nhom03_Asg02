package com.example.asg02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.asg02.controller.CreateShowtimeController;
import com.example.asg02.model.Showtime;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ManagerAddShowtimeActivity extends AppCompatActivity {
    ImageButton back;
    Spinner chooseCinemaSpinner, chooseHallSpinner, chooseMovieSpinner;
    EditText enterStartTimeEditText, enterEndTimeEditText, enterDateEditText;
    Button finishAddShowtimeButton;
    List<String> cinemaNamesList;
    List<Integer> cinemaIdList;
    List<String> hallNamesList;
    List<Integer> hallIdList;
    List<String> movieNamesList;
    List<Integer> movieIdList;
    Integer finalCinemaId;
    Integer finalHallId;
    Integer finalMovieId;
    CreateShowtimeController createShowtimeController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_add_showtime);
        back = findViewById(R.id.backShowtime);
        chooseCinemaSpinner = findViewById(R.id.chooseCinemaInShowtime);
        chooseHallSpinner = findViewById(R.id.chooseHallInShowtime);
        chooseMovieSpinner = findViewById(R.id.chooseMovieInShowtime);
        enterStartTimeEditText = findViewById(R.id.enterStartTime);
        enterEndTimeEditText = findViewById(R.id.enterEndTime);
        enterDateEditText = findViewById(R.id.enterDate);
        finishAddShowtimeButton = findViewById(R.id.finishAddShowtime);
        createShowtimeController = new CreateShowtimeController(this);
        cinemaNamesList = new ArrayList<>();
        cinemaIdList = new ArrayList<>();
        hallNamesList = new ArrayList<>();
        hallIdList = new ArrayList<>();
        movieNamesList = new ArrayList<>();
        movieIdList = new ArrayList<>();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerAddShowtimeActivity.this, ManagerActivity.class);
                startActivity(intent);
            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference("Cinemas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot cinemaSnapshot : dataSnapshot.getChildren()) {
                        // Extract cinema name
                        String cinemaName = cinemaSnapshot.child("name").getValue(String.class);
                        Integer cinemaId = cinemaSnapshot.child("id").getValue(Integer.class);
                        cinemaNamesList.add(cinemaName);
                        cinemaIdList.add(cinemaId);
                    }
                    // Create an ArrayAdapter and set it to the Spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ManagerAddShowtimeActivity.this, android.R.layout.simple_spinner_item, cinemaNamesList);
                    chooseCinemaSpinner.setAdapter(adapter);
                } else {
                    // Handle the case where "Cinemas" node doesn't exist
                    Log.d("Firebase", "Cinemas node does not exist");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Firebase", "Error reading data:", databaseError.toException());
            }
        });
        chooseCinemaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected cinema name from the Spinner
                finalCinemaId = cinemaIdList.get(position);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                database.getReference("Halls").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            hallNamesList.clear();
                            hallIdList.clear();
                            for (DataSnapshot hallSnapshot : dataSnapshot.getChildren()) {
                                // Extract cinema name
                                Integer cinemaIdInHall = hallSnapshot.child("cinemaId").getValue(Integer.class);
                                if (cinemaIdInHall.equals(finalCinemaId) ) {
                                    String hallName = hallSnapshot.child("name").getValue(String.class);
                                    Integer hallId = hallSnapshot.child("id").getValue(Integer.class);
                                    hallNamesList.add(hallName);
                                    hallIdList.add(hallId);
                                }
                                else {
                                    Toast.makeText(ManagerAddShowtimeActivity.this, "Error", Toast.LENGTH_SHORT);
                                }
                            }
                            // Create an ArrayAdapter and set it to the Spinner
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(ManagerAddShowtimeActivity.this, android.R.layout.simple_spinner_item, hallNamesList);
                            chooseHallSpinner.setAdapter(adapter);
                        } else {
                            // Handle the case where "Cinemas" node doesn't exist
                            Log.d("Firebase", "Cinemas node does not exist");
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e("Firebase", "Error reading data:", databaseError.toException());
                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where no item is selected
                Log.d("Spinner", "No item selected");
            }
        });
        chooseHallSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                finalHallId = hallIdList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where no item is selected
                Log.d("Spinner", "No item selected");
            }
        });
        database.getReference("Movies").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot movieSnapshot : dataSnapshot.getChildren()) {
                        // Extract cinema name
                        String movieName = movieSnapshot.child("name").getValue(String.class);
                        Integer movieId = movieSnapshot.child("id").getValue(Integer.class);
                        movieNamesList.add(movieName);
                        movieIdList.add(movieId);
                    }
                    // Create an ArrayAdapter and set it to the Spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ManagerAddShowtimeActivity.this, android.R.layout.simple_spinner_item, movieNamesList);
                    chooseMovieSpinner.setAdapter(adapter);
                } else {

                    Log.d("Firebase", "Cinemas node does not exist");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Firebase", "Error reading data:", databaseError.toException());
            }
        });
        chooseMovieSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected cinema name from the Spinner
                finalMovieId = movieIdList.get(position);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where no item is selected
                Log.d("Spinner", "No item selected");
            }
        });
        finishAddShowtimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = enterDateEditText.getText().toString();
                String startTime = enterStartTimeEditText.getText().toString();
                String endTime = enterEndTimeEditText.getText().toString();
                Showtime showtime = new Showtime(finalCinemaId, finalHallId, finalMovieId, startTime, endTime, date);
                createShowtimeController.createShowtime(showtime);
                Intent intent = new Intent(ManagerAddShowtimeActivity.this, ManagerActivity.class);
                startActivity(intent);
            }
        });
    }
}