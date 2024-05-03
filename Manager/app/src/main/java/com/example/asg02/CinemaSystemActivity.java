package com.example.asg02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class CinemaSystemActivity extends AppCompatActivity {
    Button HaNoiButton, TPHCMButton, HueButton;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_system);
        HaNoiButton = findViewById(R.id.Hanoi);
        TPHCMButton = findViewById(R.id.TPHCM);
        HueButton = findViewById(R.id.Hue);
        listView = findViewById(R.id.listView);
    }
}