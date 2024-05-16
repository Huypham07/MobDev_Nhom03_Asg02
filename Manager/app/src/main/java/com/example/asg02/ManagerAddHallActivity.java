package com.example.asg02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.asg02.controller.CreateHallController;
import com.example.asg02.model.Cinema;
import com.example.asg02.model.Hall;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerAddHallActivity extends AppCompatActivity {
    ImageButton back;
    EditText enterHallNameEditText, enterSeatsPerRowEditText, enterSeatsPerColumnEditText;
    Button finishAddHallButton;
    Spinner chooseCinemaSpinner;
    CreateHallController createHallController;
    List<String> cinemaNamesList;
    List<Integer> cinemaIdList;
    Integer cinemaId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_add_hall);
        back = findViewById(R.id.backHall);
        enterHallNameEditText = findViewById(R.id.enterHallName);
        enterSeatsPerRowEditText = findViewById(R.id.enterSeatsPerRow);
        enterSeatsPerColumnEditText = findViewById(R.id.enterSeatsPerColumn);
        chooseCinemaSpinner = findViewById(R.id.chooseCinemaInHall);
        finishAddHallButton = findViewById(R.id.finishAddHall);
        createHallController = new CreateHallController(this);
        cinemaNamesList = new ArrayList<>();
        cinemaIdList = new ArrayList<>();
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ManagerAddHallActivity.this, android.R.layout.simple_spinner_item, cinemaNamesList);
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
                cinemaId = cinemaIdList.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where no item is selected
                Log.d("Spinner", "No item selected");
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerAddHallActivity.this, ManagerActivity.class);
                startActivity(intent);
            }
        });
        finishAddHallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = enterHallNameEditText.getText().toString();
                Integer seatsPerRow = Integer.valueOf(enterSeatsPerRowEditText.getText().toString());
                Integer seatsPerColumn = Integer.valueOf(enterSeatsPerColumnEditText.getText().toString());
                Hall hall = new Hall(name, seatsPerRow.intValue(), seatsPerColumn.intValue(), cinemaId.intValue());
                createHallController.createHall(hall);
                Intent intent = new Intent(ManagerAddHallActivity.this, ManagerActivity.class);
                startActivity(intent);
            }
        });

    }
}