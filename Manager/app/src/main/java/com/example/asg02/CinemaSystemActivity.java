package com.example.asg02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class CinemaSystemActivity extends AppCompatActivity {
    ImageButton back;
    Button HaNoiButton, TPHCMButton, HueButton;
    ListView listView;
    String strings1[]
            = { "Star 3 Indochina Plaza Hà Nội",
            "Star 3 Indochina Plaza Hà Nội", "Star 3 Indochina Plaza Hà Nội",
            "Star 3 Indochina Plaza Hà Nội" };
    String strings2[]
            = { "Star 3 Indochina Plaza TP HCM",
            "Star 3 Indochina Plaza TP HCM", "Star 3 Indochina Plaza TP HCM",
            "Star 3 Indochina Plaza TP HCM" };
    String strings3[]
            = { "Star 3 Indochina Plaza Huế",
            "Star 3 Indochina Plaza Huế", "Star 3 Indochina Plaza Huế",
            "Star 3 Indochina Plaza Huế" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_system);
        back = findViewById(R.id.backSystem);
        HaNoiButton = findViewById(R.id.Hanoi);
        TPHCMButton = findViewById(R.id.TPHCM);
        HueButton = findViewById(R.id.Hue);
        listView = findViewById(R.id.listView);
        ArrayAdapter<String> arr1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strings1);
        ArrayAdapter<String> arr2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strings2);
        ArrayAdapter<String> arr3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strings3);
        listView.setAdapter(arr1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CinemaSystemActivity.this, ManagerActivity.class);
                startActivity(intent);
            }
        });
        HaNoiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setAdapter(arr1);
            }
        });
        TPHCMButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setAdapter(arr2);
            }
        });
        HueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setAdapter(arr3);
            }
        });
    }
}