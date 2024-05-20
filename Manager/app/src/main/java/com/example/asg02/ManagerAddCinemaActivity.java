package com.example.asg02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.asg02.controller.CreateCinemaController;
import com.example.asg02.model.Cinema;

public class ManagerAddCinemaActivity extends AppCompatActivity {
    ImageButton back;
    EditText enterCinemaNameEditText, enterLatitudeEditText, enterLongitudeEditText, enterDetailAddressEditText;
    Button finishAddCinemaButton;
    CreateCinemaController createCinemaController;
    AddCinemaLogic addCinemaLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_add_cinema);

        // Initialize views
        back = findViewById(R.id.backCinema);
        enterCinemaNameEditText = findViewById(R.id.enterCinemaName);
        enterLatitudeEditText = findViewById(R.id.enterLatitude);
        enterLongitudeEditText = findViewById(R.id.enterLongtitude);
        enterDetailAddressEditText = findViewById(R.id.enterDetailAddress);
        finishAddCinemaButton = findViewById(R.id.finishAddCinema);

        // Initialize controller and logic
        createCinemaController = new CreateCinemaController(this);
        SharedPreferences preferences = getSharedPreferences("account_info", MODE_PRIVATE);
        addCinemaLogic = new AddCinemaLogic(createCinemaController, preferences);

        // Set listeners
        back.setOnClickListener(v -> navigateToManagerActivity());
        finishAddCinemaButton.setOnClickListener(view -> onAddCinemaButtonClick());
    }

    private void navigateToManagerActivity() {
        Intent intent = new Intent(ManagerAddCinemaActivity.this, ManagerActivity.class);
        startActivity(intent);
    }

    private void onAddCinemaButtonClick() {
        String name = enterCinemaNameEditText.getText().toString();
        double latitude = Double.valueOf(enterLatitudeEditText.getText().toString()).doubleValue();
        double longitude = Double.valueOf(enterLongitudeEditText.getText().toString()).doubleValue();
        String detailAddress = enterDetailAddressEditText.getText().toString();
        addCinemaLogic.handleAddCinema(name, latitude, longitude, detailAddress);
        navigateToManagerActivity();
    }
}