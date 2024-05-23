package com.example.asg02.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.asg02.R;
import com.example.asg02.controller.CreateCinemaController;
import com.example.asg02.logic.AddCinemaLogic;

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

        try {
            String name = enterCinemaNameEditText.getText().toString();
            double latitude = Double.valueOf(enterLatitudeEditText.getText().toString());
            double longitude = Double.valueOf(enterLongitudeEditText.getText().toString()).doubleValue();
            String detailAddress = enterDetailAddressEditText.getText().toString();
            if (name.isEmpty() || detailAddress.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_LONG);
            } else {
                addCinemaLogic.handleAddCinema(name, latitude, longitude, detailAddress);
                navigateToManagerActivity();
            }
        } catch (NumberFormatException e) {
            Log.e("LatitudeError", "Invalid latitude format", e);
            Toast.makeText(this, "Lỗi định dạng kinh độ, vĩ độ", Toast.LENGTH_SHORT).show();
        }
    }
}