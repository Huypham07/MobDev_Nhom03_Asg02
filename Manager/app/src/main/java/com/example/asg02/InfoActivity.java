package com.example.asg02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class InfoActivity extends AppCompatActivity {
    ImageView backImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        backImageView = findViewById(R.id.back);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, ManagerActivity.class);
                startActivity(intent);
            }

        });
    }
}