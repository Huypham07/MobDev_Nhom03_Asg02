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
import android.widget.TextView;
import android.widget.Toast;

import com.example.asg02.controller.EditAndDeleteController;
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
    TextView showInforCinemaHallShowTextView;
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
    EditAndDeleteController editAndDeleteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_edit_and_delete);
        back = findViewById(R.id.backEditAndDelete);
        chooseCinemaSpinner = findViewById(R.id.chooseCinemaInEditAndDelete);
        chooseHallSpinner = findViewById(R.id.chooseHallInEditAndDelete);
        chooseShowSpinner = findViewById(R.id.chooseShowInEditAndDelete);
        showInforCinemaHallShowTextView = findViewById(R.id.showInfoOfCinemaHallShow);
        editButton = findViewById(R.id.edit);
        deleteButton = findViewById(R.id.delete);
        cinemaNamesList = new ArrayList<>();
        cinemaList = new ArrayList<>();
        hallNamesList = new ArrayList<>();
        hallList = new ArrayList<>();
        showTimeList = new ArrayList<>();
        showList = new ArrayList<>();
        editAndDeleteController = new EditAndDeleteController(this);
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
                        Integer id = cinemaSnapshot.child("id").getValue(Integer.class);
                        String name = cinemaSnapshot.child("name").getValue(String.class);
                        String province = cinemaSnapshot.child("province").getValue(String.class);
                        String district = cinemaSnapshot.child("district").getValue(String.class);
                        String commune = cinemaSnapshot.child("commune").getValue(String.class);
                        String detailAddress = cinemaSnapshot.child("detailAddress").getValue(String.class);
                        String manager = cinemaSnapshot.child("manager").getValue(String.class);
                        Cinema cinema = new Cinema(id.intValue(), name, province, district, commune, detailAddress, manager);
                        cinemaNamesList.add(name);
                        cinemaList.add(cinema);
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
                finalCinemaId = (Integer) cinemaList.get(position).getId();
                deleteId = finalCinemaId;
                deleteType = "Cinemas";
                showInforCinemaHallShowTextView.setText("Cinema info: "
                        + "\nname: " + cinemaList.get(position).getName()
                        + "\nprovince: " + cinemaList.get(position).getProvince()
                        + "\ndistrict: " + cinemaList.get(position).getDistrict()
                        + "\ncommune: " + cinemaList.get(position).getCommune()
                        + "\ndetailAddress: " + cinemaList.get(position).getDetailAddress());
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                database.getReference("Halls").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            hallNamesList.clear();
                            hallList.clear();
                            showTimeList.clear();
                            showList.clear();
                            ArrayAdapter<String> adapter1 = new ArrayAdapter<>(ManagerEditAndDeleteActivity.this, android.R.layout.simple_spinner_item, showTimeList);
                            chooseShowSpinner.setAdapter(adapter1);
                            for (DataSnapshot hallSnapshot : dataSnapshot.getChildren()) {
                                // Extract cinema name
                                Integer cinemaIdInHall = hallSnapshot.child("cinemaId").getValue(Integer.class);
                                if (cinemaIdInHall.equals(finalCinemaId) ) {
                                    String name = hallSnapshot.child("name").getValue(String.class);
                                    Integer id = hallSnapshot.child("id").getValue(Integer.class);
                                    Integer seatsPerRow = hallSnapshot.child("seatsPerRow").getValue(Integer.class);
                                    Integer seatsPerColumn = hallSnapshot.child("seatsPerColumn").getValue(Integer.class);
                                    Integer cinemaId = hallSnapshot.child("cinemaId").getValue(Integer.class);
                                    hallNamesList.add(name);
                                    hallList.add(new Hall(id.intValue(), name, seatsPerRow.intValue(), seatsPerColumn.intValue(), cinemaId.intValue()));
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
                finalHallId = (Integer) hallList.get(position).getId();
                deleteId = finalHallId;
                deleteType = "Halls";
                showInforCinemaHallShowTextView.setText("Hall info: "
                        + "\nname: " + hallList.get(position).getName()
                        + "\nseatsPerRow: " + hallList.get(position).getSeatsPerRow()
                        + "\nseatsPerColumn: " + hallList.get(position).getSeatPerColumn());
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                database.getReference("Shows").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            showTimeList.clear();
                            showList.clear();
                            for (DataSnapshot showSnapshot : dataSnapshot.getChildren()) {
                                // Extract cinema name
                                Integer hallIdInShow = showSnapshot.child("hallId").getValue(Integer.class);
                                if (hallIdInShow.equals(finalHallId) ) {
                                    Integer id = showSnapshot.child("id").getValue(Integer.class);
                                    Integer cinemaId = showSnapshot.child("cinemaId").getValue(Integer.class);
                                    Integer hallId = showSnapshot.child("hallId").getValue(Integer.class);
                                    Integer movieId = showSnapshot.child("movieId").getValue(Integer.class);
                                    String startTime = showSnapshot.child("startTime").getValue(String.class);
                                    String endTime = showSnapshot.child("endTime").getValue(String.class);
                                    String date = showSnapshot.child("date").getValue(String.class);
                                    showTimeList.add(startTime + ":" + endTime + " " + date);
                                    showList.add(new Show(id.intValue(), cinemaId.intValue(), hallId.intValue(), movieId.intValue(), startTime, endTime, date));
                                }
                                else {
                                    Toast.makeText(ManagerEditAndDeleteActivity.this, "Error", Toast.LENGTH_SHORT);
                                }
                            }
                            // Create an ArrayAdapter and set it to the Spinner
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(ManagerEditAndDeleteActivity.this, android.R.layout.simple_spinner_item, showTimeList);
                            chooseShowSpinner.setAdapter(adapter);
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
        chooseShowSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected cinema name from the Spinner
                finalShowId = showList.get(position).getId();
                deleteId = finalShowId;
                deleteType = "Shows";
                showInforCinemaHallShowTextView.setText("Show info: "
                        + "\nstartTime: " + showList.get(position).getStartTime()
                        + "\nendTime: " + showList.get(position).getEndTime()
                        + "\ndate: " + showList.get(position).getDate()
                        + "\nmovieId: " + showList.get(position).getMovieId()
                        + "\nhallId: " + showList.get(position).getHallId());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where no item is selected
                Log.d("Spinner", "No item selected");
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editAndDeleteController.delete(deleteId, deleteType);
            }
        });
    }
}