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
    EditText enterCinemaNameEditText, enterLatitudeEditText, enterLongtitudeEditText,
            enterDetailAddressEditText;
    Button finishAddCinemaButton;
    CreateCinemaController createCinemaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_add_cinema);
        back = findViewById(R.id.backCinema);
        enterCinemaNameEditText = findViewById(R.id.enterCinemaName);
        enterLatitudeEditText = findViewById(R.id.enterLatitude);
        enterLongtitudeEditText = findViewById(R.id.enterLongtitude);
        enterDetailAddressEditText = findViewById(R.id.enterDetailAddress);
        finishAddCinemaButton = findViewById(R.id.finishAddCinema);
        createCinemaController = new CreateCinemaController(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(ManagerAddCinemaActivity.this, ManagerActivity.class);
                startActivity(intent);
            }
        });
        finishAddCinemaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = enterCinemaNameEditText.getText().toString();
                Double latitude = Double.valueOf(enterLatitudeEditText.getText().toString());
                Double longitude = Double.valueOf(enterLongtitudeEditText.getText().toString());
                String detailAddress = enterDetailAddressEditText.getText().toString();
                SharedPreferences preferences = getSharedPreferences("account_info", MODE_PRIVATE);
                String email = preferences.getString("email", "error"); // Provide a default value if email is not found
                if (email != null) {
                    // Email was found in SharedPreferences
                    Log.d("SharedPrefs", "Email retrieved: " + email);
                    // Use the retrieved email here (e.g., display it, send it to a server)
                } else {
                    // Email was not found in SharedPreferences
                    Log.d("SharedPrefs", "Email not found in SharedPreferences");
                    // Handle the case where email is not available (e.g., prompt user to log in)
                }
                Cinema cinema = new Cinema(name, latitude.doubleValue(), longitude.doubleValue(), detailAddress, email);
                createCinemaController.createCinema(cinema);
                Intent intent = new Intent(ManagerAddCinemaActivity.this, ManagerActivity.class);
                startActivity(intent);
            }
        });;
    }
}