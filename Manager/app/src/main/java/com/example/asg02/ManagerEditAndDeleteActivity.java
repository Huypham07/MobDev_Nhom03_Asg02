package com.example.asg02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;

import com.example.asg02.model.Cinema;
import com.example.asg02.model.Hall;
import com.example.asg02.model.Show;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ManagerEditAndDeleteActivity extends AppCompatActivity {
    ImageButton back;
    Spinner chooseCinemaSpinner, chooseHallSpinner, chooseShowSpinner;
    Button editButton, deleteButton;
    List<String> cinemaNamesList;
    List<Cinema> cinemaList;
    List<String> hallNamesList;
    List<Hall> hallList;
    List<String> showTimeList;
    List<Show> showList;
    Integer finalCinemaId;
    Integer finalHallId;
    Integer finalShowId;
    Integer deleteId;
    String deleteType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_edit_and_delete);
        back = findViewById(R.id.backEditAndDelete);
        chooseCinemaSpinner = findViewById(R.id.chooseCinemaInEditAndDelete);
        chooseHallSpinner = findViewById(R.id.chooseHallInEditAndDelete);
        chooseShowSpinner = findViewById(R.id.chooseShowInEditAndDelete);
        editButton = findViewById(R.id.edit);
        deleteButton = findViewById(R.id.delete);
        cinemaNamesList = new ArrayList<>();
        cinemaList = new ArrayList<>();
        hallNamesList = new ArrayList<>();
        hallList = new ArrayList<>();
        showTimeList = new ArrayList<>();
        showList = new ArrayList<>();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerEditAndDeleteActivity.this, ManagerActivity.class);
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ManagerEditAndDeleteActivity.this, android.R.layout.simple_spinner_item, cinemaNamesList);
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
                                    Toast.makeText(ManagerEditAndDeleteActivity.this, "Error", Toast.LENGTH_SHORT);
                                }
                            }
                            // Create an ArrayAdapter and set it to the Spinner
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(ManagerEditAndDeleteActivity.this, android.R.layout.simple_spinner_item, hallNamesList);
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
                // Get the selected cinema name from the Spinner
                finalHallId = hallIdList.get(position);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                database.getReference("Shows").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            showTimeList.clear();
                            hallIdList.clear();
                            for (DataSnapshot hallSnapshot : dataSnapshot.getChildren()) {
                                // Extract cinema name
                                Integer cinemaIdInHall = hallSnapshot.child("hallId").getValue(Integer.class);
                                if (cinemaIdInHall.equals(finalCinemaId) ) {
                                    String hallName = hallSnapshot.child("name").getValue(String.class);
                                    Integer hallId = hallSnapshot.child("id").getValue(Integer.class);
                                    hallNamesList.add(hallName);
                                    hallIdList.add(hallId);
                                }
                                else {
                                    Toast.makeText(ManagerEditAndDeleteActivity.this, "Error", Toast.LENGTH_SHORT);
                                }
                            }
                            // Create an ArrayAdapter and set it to the Spinner
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(ManagerEditAndDeleteActivity.this, android.R.layout.simple_spinner_item, hallNamesList);
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
    }
}