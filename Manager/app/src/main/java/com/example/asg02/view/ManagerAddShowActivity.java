package com.example.asg02.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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

import com.example.asg02.R;
import com.example.asg02.controller.CreateShowController;
import com.example.asg02.model.Show;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ManagerAddShowActivity extends AppCompatActivity {
    ImageButton back;
    Spinner chooseCinemaSpinner, chooseHallSpinner, chooseMovieSpinner;
    EditText enterStartTimeEditText, enterEndTimeEditText, enterDateEditText;
    Button finishAddShowButton;
    List<String> cinemaNamesList;
    List<Integer> cinemaIdList;
    List<String> hallNamesList;
    List<Integer> hallIdList;
    List<String> movieNamesList;
    List<Integer> movieIdList;
    Integer finalCinemaId;
    Integer finalHallId;
    Integer finalMovieId;
    CreateShowController createShowController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_add_show);
        back = findViewById(R.id.backShow);
        chooseCinemaSpinner = findViewById(R.id.chooseCinemaInShow);
        chooseHallSpinner = findViewById(R.id.chooseHallInShow);
        chooseMovieSpinner = findViewById(R.id.chooseMovieInShow);
        enterStartTimeEditText = findViewById(R.id.enterStartTime);
        enterEndTimeEditText = findViewById(R.id.enterEndTime);
        enterDateEditText = findViewById(R.id.enterDate);
        enterDateEditText.setOnClickListener(v -> showDatePickerDialog());
        finishAddShowButton = findViewById(R.id.finishAddShow);
        createShowController = new CreateShowController(this);
        cinemaNamesList = new ArrayList<>();
        cinemaIdList = new ArrayList<>();
        hallNamesList = new ArrayList<>();
        hallIdList = new ArrayList<>();
        movieNamesList = new ArrayList<>();
        movieIdList = new ArrayList<>();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerAddShowActivity.this, ManagerActivity.class);
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ManagerAddShowActivity.this, android.R.layout.simple_spinner_item, cinemaNamesList);
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
                                    Toast.makeText(ManagerAddShowActivity.this, "Error", Toast.LENGTH_SHORT);
                                }
                            }
                            // Create an ArrayAdapter and set it to the Spinner
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(ManagerAddShowActivity.this, android.R.layout.simple_spinner_item, hallNamesList);
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ManagerAddShowActivity.this, android.R.layout.simple_spinner_item, movieNamesList);
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
        finishAddShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = enterDateEditText.getText().toString();
                String startTime = enterStartTimeEditText.getText().toString();
                String endTime = enterEndTimeEditText.getText().toString();
                if (date.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
                    Toast.makeText(ManagerAddShowActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_LONG);
                } else {
                    Show show = new Show(finalCinemaId.intValue(), finalHallId.intValue(), finalMovieId.intValue(), startTime, endTime, date);
                    createShowController.createShow(show);
                    Intent intent = new Intent(ManagerAddShowActivity.this, ManagerActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                ManagerAddShowActivity.this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1;
                    enterDateEditText.setText(selectedDate);
                },
                year, month, day
        );
        datePickerDialog.show();
    }

}